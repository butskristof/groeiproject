package be.kdg.burgemeesterproject.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kristof Buts
 * @version 1.0 11/13/18 6:05 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CanRun {
	String value() default "dummy";
}
