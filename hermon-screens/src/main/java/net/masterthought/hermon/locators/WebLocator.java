package net.masterthought.hermon.locators;

import org.openqa.selenium.By;

public class WebLocator implements Locator<By> {

    private By value;

    @Override
    public LocatorType getType() {
        return LocatorType.WEB;
    }

    @Override
    public By value() {
        return value;
    }

    @Override
    public void setValue(By value) {
        this.value = value;
    }

    public WebLocator withValue(By value){
        setValue(value);
        return this;
    }
}
