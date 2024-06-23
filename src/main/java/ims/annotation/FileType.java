package ims.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Payload;

/**
 * Validate file type
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileType {

	/**
	 * Generate error messages
	 *
	 * @return error messages
	 */
	String message() default "{EMSG102}";

	/**
	 * Apply different validations depending on the group
	 *
	 * @return default group
	 */
	Class<?>[] groups() default {};

	/**
	 * Give meta data to the object to be validated.
	 *
	 * @return meta data
	 */
	Class<? extends Payload>[] payload() default {};
}