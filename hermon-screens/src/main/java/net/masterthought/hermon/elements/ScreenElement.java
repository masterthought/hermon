package net.masterthought.hermon.elements;

import net.masterthought.hermon.locators.Locator;

public class ScreenElement implements Element {

    private String alias;
    private String defaultValue;
    private Locator locator;

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Locator getLocator() {
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

    public ScreenElement withLocator(Locator locator){
        this.locator = locator;
        return this;
    }
}
