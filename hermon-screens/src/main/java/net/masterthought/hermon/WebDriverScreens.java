package net.masterthought.hermon;

import net.masterthought.hermon.annotations.Alias;
import net.masterthought.hermon.annotations.DefaultValue;
import net.masterthought.hermon.annotations.LocateBy;
import net.masterthought.hermon.annotations.Url;
import net.masterthought.hermon.elements.Element;
import net.masterthought.hermon.elements.ScreenElement;
import net.masterthought.hermon.locators.WebDriverLocator;
import net.masterthought.hermon.screens.WebScreen;
import net.masterthought.hermon.screens.Screen;
import org.openqa.selenium.By;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class WebDriverScreens {

    public static <T extends Screen> T init(T screen) {
        if (screen.getClass().isAnnotationPresent(Url.class)) {
            String url = screen.getClass().getAnnotation(Url.class).value();
            ((WebScreen) screen).setUrl(url);
        }
        Field[] fields = screen.getClass().getFields();
        for (Field field : fields) {
            try {
                instantiateAndRemember(screen, field);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("I wasn't able to instantiate your " + screen.getClass() + ". Issue is:\n" + e.getMessage());
            }
        }
        return screen;
    }


    private static <T extends Screen> void instantiateAndRemember(T screen, Field field) throws IllegalAccessException {
        String aliasValue = field.isAnnotationPresent(Alias.class) ? field.getAnnotation(Alias.class).value() : null;
        String defaultValue = field.isAnnotationPresent(DefaultValue.class) ? field.getAnnotation(DefaultValue.class).value() : null;

        if (isElement(field)) {
            By by = getBy(field);
            ScreenElement screenElement = new ScreenElement().withAlias(aliasValue).withDefaultValue(defaultValue).withLocator(new WebDriverLocator(by));
            field.set(screen, screenElement);
            screen.addScreenElement(screenElement);
        }
    }

    private static boolean isElement(Field field) {
        List<Class<?>> interfaces = Arrays.asList(field.getType().getInterfaces());
        return interfaces.contains(Element.class);
    }

    private static boolean isScreen(Field field) {
        List<Class<?>> interfaces = Arrays.asList(field.getType().getInterfaces());
        return interfaces.contains(Screen.class);
    }

    private static By getBy(Field field) {
        boolean locatorAnnotationIsPresent = field.isAnnotationPresent(LocateBy.class);
        LocateBy annotation = field.getAnnotation(LocateBy.class);
        if (locatorAnnotationIsPresent) {

            if (isNotEqualToDefaultValue(annotation.id())) {
                return By.id(annotation.id());
            }
            if (isNotEqualToDefaultValue(annotation.name())) {
                return By.name(annotation.name());
            }
            if (isNotEqualToDefaultValue(annotation.xpath())) {
                return By.xpath(annotation.xpath());
            }
            if (isNotEqualToDefaultValue(annotation.css())) {
                return By.cssSelector(annotation.css());
            }
            if (isNotEqualToDefaultValue(annotation.className())) {
                return By.className(annotation.className());
            }
            if (isNotEqualToDefaultValue(annotation.linkText())) {
                return By.linkText(annotation.linkText());
            }
            if (isNotEqualToDefaultValue(annotation.partialLinkText())) {
                return By.partialLinkText(annotation.partialLinkText());
            }
        }
        throw new RuntimeException("Couldn't initialize Screen");
    }

    private static boolean isNotEqualToDefaultValue(String s) {
        return !s.equals(LocateBy.defaultValue);
    }

}

