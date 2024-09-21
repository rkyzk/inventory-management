package ims.entity;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import ims.annotation.FileName;
import ims.annotation.FileSize;
import ims.annotation.FileType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * プロダクト model.
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
@NoArgsConstructor
@Data
public class Product {
	/** プロダクト ID */
	private int id;
	
	/** プロダクト名 */
	@NotBlank
	@Size(max = 40, message = "{max}文字以内で記入してください")
	private String name;
	
	/** カテゴリー */
	@Min(0)
	@Max(20)
	private int categoryId;
	
	/** 色 */
	@Min(0)
	@Max(20)
	private int colorId;
	
	/** 値段 */
	@Min(0)
	private int price;
	
	/** 在庫数 */
	@Min(0)
	@Max(99999)
	private int stock;
	
	/** 商品説明 */
	@Size(max = 200, message = "{max} 文字以内で記入してください")
	private String description;
	
	/** 画像ファイル名 */
	private String imageName;

	/** ファイルパス */
	private String imagePath;
	
	/** 画像ファイル */
	@FileName(maxLength=30)
	@FileType
	@FileSize(maxSize = 819200)
	private MultipartFile multipartFile;

	/** 登録日 */
	private LocalDateTime createdAt;

	/** 更新日 */
	private LocalDateTime updatedAt;

	/** 削除日 */
	private LocalDateTime deletedAt;
}
