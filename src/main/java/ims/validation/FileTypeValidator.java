package ims.validation;

import org.springframework.web.multipart.MultipartFile;

import ims.annotation.FileType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Generate file type.
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
public class FileTypeValidator implements ConstraintValidator<FileType, MultipartFile> {
	/**
	 * Check if the file is jpeg or png.
	 *
	 * @param value: image file
	 * @param context 
	 * @return If the file doesn't exist, or the type is jpeg or png, return true,
	 *         otherwise false.
	 */
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return (value == null || value.isEmpty() ||
				value.getContentType().equals("image/png") || 
				value.getContentType().equals("image/jpeg") || 
				value.getContentType().equals("image/jpg"));
	}
}
