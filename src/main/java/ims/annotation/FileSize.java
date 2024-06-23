package ims.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ims.validation.FileSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


/**
 * Check file size.
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
@Constraint(validatedBy=FileSizeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {

	/**
	 * Set max file size.
	 *
	 * @return max file size
	 */
	int maxSize();

	/**
	 * Generate error messages.
	 *
	 * @return error message
	 */
	String message() default "{EMSG103}";

	/**
	 * Apply different validation depending on groups.
	 *
	 * @return default group
	 */
	Class<?>[] groups() default {};

	/**
	 * Provide meta data to the object being validated.
	 *
	 * @return meta data
	 */
	Class<? extends Payload>[] payload() default {};
}
