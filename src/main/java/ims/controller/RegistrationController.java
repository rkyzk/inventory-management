package ims.controller;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ims.entity.Product;
import ims.enums.CategoryEnum;
import ims.service.ImageUploadService;
import ims.service.ProductService;
import jakarta.validation.Valid;

/**
 * 登録画面のコントローラークラス
 */
@Controller
public class RegistrationController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ImageUploadService imgUploadService;
	
	@Autowired
	MessageSource msg;

	/**
	 * 登録画面を表示.
	 *
	 * @param  model
	 * @param  product
	 * @return 登録画面
	 */
	@GetMapping("/product-registration")
	public String getProductRegistration(Model model,
			@ModelAttribute("product") Product product) {
		return "product-registration";
	}
	
	/**
	 * 入力データを検証し、エラーがあるときはエラーメッセージを表示.
	 * 画像ファイルを s3 bucket にアップロード.
	 * DBに商品データを格納.
	 *
	 * @param model
	 * @param locale
	 * @param redirectAttributes
	 * @param product
	 * @param bindingResult
	 */
	@PostMapping("/product-registration")
	public String postProduct(Model model, Locale locale,
			RedirectAttributes redirectAttributes,
		    @ModelAttribute("product") @Valid Product product,
			BindingResult bindingResult) throws IOException {
		// 入力エラーがあるときは登録画面を再表示
		if (bindingResult.hasErrors()) {
			return "product-registration";
		}
		// 登録完了メッセージを設定（登録エラー発生時は先の処理でメッセージを変更）
		String message = "";
		message = msg.getMessage("REGSUC", null, locale);
		// 画像がアップロードされたとき
		if (product.getMultipartFile() != null && !product.getMultipartFile().isEmpty()) {
			// ファイル名取得
			String imageName = product.getMultipartFile().getOriginalFilename();
			// カテゴリー名取得
			String categoryName = CategoryEnum.getValueByCode(product.getCategoryId()).getCategoryEn();
			// S3 bucketに画像を格納
			String imagePath = imgUploadService.uploadImg(
					product.getMultipartFile(),
					categoryName,
					imageName);
			// 画像アップロードに失敗のとき該当メッセージを設定
			if (imagePath == null) { 
				message = msg.getMessage("IMGUPLERR", null, locale);
			} else {
				// 画像ファイル名、パスを設定
				product.setImageName(imageName);
			    product.setImagePath(imagePath);
			}
		}
		// DBに商品データを格納
	    try {
	    	productService.insertProduct(product);
	    } catch (Exception e) {
	    	// 登録エラーメッセージを設定
	        message = msg.getMessage("REGERR", null, locale);
	    }
	    // リストコントローラに送るメッセージをredirectAttributesに設定
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/product-list";
	}
}
