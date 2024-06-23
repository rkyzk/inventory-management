package ims.validation;

import org.springframework.web.multipart.MultipartFile;

import ims.annotation.FileName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


/**
 * Check file name length
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
public class FileNameValidator implements ConstraintValidator<FileName, MultipartFile> {
	/** max file name length */
	int maxLength;

	/**
	 * Receive annotation
	 *
	 * @param annotation
	 */
	@Override
	public void initialize(FileName annotation) {
		this.maxLength = annotation.maxLength();
	}

	/**
	 * Validate the file name length is
	 * less than a given value.
	 *
	 * @param value: image file
	 * @param context
	 * @return If file doesn't exist or the file name is less than the given value
	 *         return true, otherwise false.
	 */
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		if (value != null) {
			return (value.getOriginalFilename().length() <= this.maxLength);
		} else {
			return true;
		}
	}
}