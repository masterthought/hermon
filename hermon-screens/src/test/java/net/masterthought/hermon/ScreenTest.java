package net.masterthought.hermon;

import net.masterthought.hermon.annotations.Alias;
import net.masterthought.hermon.annotations.DefaultValue;
import net.masterthought.hermon.annotations.LocateBy;
import net.masterthought.hermon.annotations.Url;
import net.masterthought.hermon.elements.ScreenElement;
import net.masterthought.hermon.locators.WebDriverLocator;
import net.masterthought.hermon.screens.WebScreen;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ScreenTest {

    @Url("http://privateurl.com")
    private class MyScreen extends WebScreen {
        @DefaultValue("my value 1")
        @Alias("Element By ID")
        @LocateBy(id = "test")
        public ScreenElement elementLocatedById;

        @DefaultValue("my value 2")
        @Alias("Element By XPATH")
        @LocateBy(xpath = "//*[@id='test']")
        public ScreenElement elementLocatedByXpath;

    }

    @Test
    public void shouldRetrieveElementById() throws Exception {
        MyScreen myScreen = WebDriverScreens.init(new MyScreen());
        assertThat(myScreen.screenElements().size(), is(2));
        assertThat(((WebDriverLocator) myScreen.elementLocatedById.getLocator()).value(), is(By.id("test")));
        assertThat(myScreen.elementLocatedById.getAlias(), is("Element By ID"));
        assertThat(myScreen.elementLocatedById.getDefaultValue(), is("my value 1"));
    }

    @Test
    public void shouldRetrieveElementByXpath() throws Exception {
        MyScreen myScreen = WebDriverScreens.init(new MyScreen());
        assertThat(myScreen.screenElements().size(), is(2));
        assertThat(((WebDriverLocator) myScreen.elementLocatedByXpath.getLocator()).value(), is(By.xpath("//*[@id='test']")));
        assertThat(myScreen.elementLocatedByXpath.getAlias(), is("Element By XPATH"));
        assertThat(myScreen.elementLocatedByXpath.getDefaultValue(), is("my value 2"));
    }

    @Test
    public void shouldRetrieveElementByAlias() throws Exception {
        MyScreen myScreen = WebDriverScreens.init(new MyScreen());
        ScreenElement screenElement = myScreen.getScreenElementWithAlias("Element By ID");
        assertThat(((WebDriverLocator) screenElement.getLocator()).value(), is(By.id("test")));
        assertThat(screenElement.getAlias(), is("Element By ID"));
        assertThat(screenElement.getDefaultValue(), is("my value 1"));
    }

    @Test
    public void shouldRetrieveUrlOfPage() throws Exception {
        MyScreen myScreen = WebDriverScreens.init(new MyScreen());
        assertThat(myScreen.getUrl(), is("http://privateurl.com"));
    }

}
