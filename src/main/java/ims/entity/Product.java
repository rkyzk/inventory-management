package ims.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import ims.annotation.FileName;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for 'Product' model.
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
@NoArgsConstructor
@Data
public class Product {
	/** Product ID */
	private int id;
	
	/** Product name */
	@NotBlank
	@Size(max = 40, message = "must not exceed {max} characters")
	private String name;
	
	/** Category */
	@NotNull
	private String categoryId;
	
	/** Quantity per package */
	@NotNull
	@Min(1)
	@Max(9999)
	private int quantity;
	
	/** Price */
	@NotNull(message = "must not be blank")
	@DecimalMin(value = "0.00", inclusive = true)
	@Digits(integer=5, fraction=2)
	private BigDecimal price;
	
	/** Stock */
	@Min(0)
	@Max(99999)
	private int stock;
	
	/** Description */
	@Size(max = 200, message = "must not exceed {max} characters")
	private String description;
	
	/** image file name */
	private String imageName;

	/** image file path */
	private String imagePath;
	
	/** image file (not to be inserted in DB) */
	@FileName(maxLength=30)
	private MultipartFile multipartFile;

	/** created at */
	private LocalDateTime createdAt;

	/** updated at */
	private LocalDateTime updatedAt;

	/** deleted at */
	private LocalDateTime deletedAt;
}
