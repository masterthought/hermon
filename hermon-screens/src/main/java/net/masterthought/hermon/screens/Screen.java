package net.masterthought.hermon.screens;

import net.masterthought.hermon.elements.ScreenElement;

public interface Screen {

    public ScreenElement getScreenElementWithAlias(String alias);

    public void addScreenElement(ScreenElement screenElement);
}
