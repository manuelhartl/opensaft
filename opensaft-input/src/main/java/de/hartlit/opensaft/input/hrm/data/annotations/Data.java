package de.hartlit.opensaft.input.hrm.data.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.hartlit.opensaft.input.hrm.data.Unit;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Data {

	byte scale() default 1;

	Unit unit() default Unit.UNDEFINED;
	
	String dateformat() default "";

}
