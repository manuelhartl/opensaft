package de.hartlit.opensaft.input.hrm.data.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MatrixBinding {

	byte row();

	byte col();
}
