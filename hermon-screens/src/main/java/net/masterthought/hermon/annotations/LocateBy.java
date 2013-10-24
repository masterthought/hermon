package net.masterthought.hermon.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocateBy {

    public static String defaultValue = "[unassigned]";
    public String id() default defaultValue;
    public String name() default defaultValue;
    public String xpath() default defaultValue;
    public String linkText() default defaultValue;
    public String css() default defaultValue;
    public String className() default defaultValue;
    public String attribute() default defaultValue;
    public String partialLinkText() default defaultValue;
}
