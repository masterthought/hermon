package net.masterthought.hermon.elements;

import net.masterthought.hermon.locators.Locator;
import org.openqa.selenium.By;

public class ScreenElement implements Element {

    private String alias;
    private String defaultValue;
    private Locator<By> locator;

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Locator<By> getLocator() {
        return locator;
    }

    public ScreenElement withAlias(String alias){
        this.alias = alias;
        return this;
    }

    public ScreenElement withDefaultValue(String defaultValue){
        this.defaultValue = defaultValue;
        return this;
    }

    public ScreenElement withLocator(Locator<By> locator){
        this.locator = locator;
        return this;
    }
}
