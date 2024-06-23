package ims.validation;

import org.springframework.web.multipart.MultipartFile;

import ims.annotation.FileSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


/**
 * Check file name length
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
	/** max file name length */
	int maxSize;

	/**
	 * Receive annotation
	 *
	 * @param annotation
	 */
	@Override
	public void initialize(FileSize annotation) {
		this.maxSize = annotation.maxSize();
	}

	/**
	 * Validate the file size is
	 * less than a given value.
	 *
	 * @param value: image file
	 * @param context
	 * @return If file doesn't exit or the size is less than the given value,
	 *         return true, otherwise false.
	 */
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		if (value != null) {
			return (this.maxSize >= value.getSize());
		} else {
			return true;
		}
	}
}