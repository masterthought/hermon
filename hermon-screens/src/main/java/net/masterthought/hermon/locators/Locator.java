package net.masterthought.hermon.locators;

public interface Locator<T> {

    public LocatorType getType();
    public T value();
    public void setValue(T t);

}
