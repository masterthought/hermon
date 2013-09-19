package net.masterthought.hermon.elements;

import net.masterthought.hermon.locators.Locator;

public interface Element {

    String getAlias();

    String getDefaultValue();

    Locator getLocator();
}
