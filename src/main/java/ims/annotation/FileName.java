package ims.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Payload;

/**
 * Validate file name length.
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileName {

	/**
	 * set max file name length
	 *
	 * @return max size
	 */
	int maxLength();

	/**
	 * Generate error messages
	 *
	 * @return error message
	 */
	String message() default "{EMSG101}";

	/**
	 * Set different criteria for different groups
	 *
	 * @return default
	 */
	Class<?>[] groups() default {};

	/**
	 * Provide meta data for object being validated.
	 *
	 * @return meta data
	 */
	Class<? extends Payload>[] payload() default {};
}