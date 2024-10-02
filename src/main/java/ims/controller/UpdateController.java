package ims.controller;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ims.entity.Product;
import ims.enums.CategoryEnum;
import ims.service.ImageUploadService;
import ims.service.ProductService;
import jakarta.validation.Valid;

@Controller
public class UpdateController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ImageUploadService imgUploadService;
	
	@Autowired
	MessageSource msg;
	
	@Value("${aws.endpoint.url}")
	private String endpoint;
	
	/**
	 * DBから商品データを取得し更新ページを表示.
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/product-update")
	public String getUpdate(Model model,
			@RequestParam("id") int id) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("awsUrl", endpoint);
		return "product-update";	
	}
	
	/**
	 * 入力データを検証し、
	 * 画像をS3 bucketにアップロードまたは削除.
	 * DBの商品データを更新.
	 * 
	 * @param model
	 * @param locale
	 * @param currImg
	 * @param redirectAttributes
	 * @param product
	 * @param bindingResult
	 * @return
	 * @throws IOException
	 */
	@PutMapping("/product-update")
	public String postUpdate(Model model, Locale locale,
			@RequestParam(name="curr-img", required=false) boolean currImg,
			RedirectAttributes redirectAttributes,
			@ModelAttribute @Valid Product product,
			BindingResult bindingResult) throws IOException {
		// 更新成功メッセージを設定（失敗時はあとで変更）
		String message = msg.getMessage("UPDSUC", null, locale);
		// 入力エラーの時は更新ページを表示
		if (bindingResult.hasErrors()) {
			return "product-update";
		}
		// 画像が削除されたときはS3 bucketから画像を削除し、
		// imagePath と imageName をnullに設定
		if (currImg) {
			boolean imgDeleted = imgUploadService.deleteImg(product.getImagePath());
			if (imgDeleted) {
			    product.setImagePath(null);
			    product.setImageName(null);
			} else {
				// 「画像が削除されませんでした。」のメッセージを表示
				message = msg.getMessage("IMGDELERR", null, locale);
			}
		}
		// 画像が登録された場合 S3 bucketにアップロード
		if(product.getMultipartFile() != null && !product.getMultipartFile().isEmpty()) {
		    String imageName = product.getMultipartFile().getOriginalFilename();
		    String categoryName = CategoryEnum.getValueByCode(product.getCategoryId()).getCategoryEn();
		    String imagePath = imgUploadService.uploadImg(
				product.getMultipartFile(),
				categoryName,
				imageName);
		    if (imagePath == null) {
				// 「商品データは保存されましたが画像は保存されませんでした。」の
		    	//　メッセージを設定。
				message = msg.getMessage("IMGUPLERR", null, locale);		    	
		    } else {
		    	// imageName と imagePathを設定
			    product.setImageName(imageName);
			    product.setImagePath(imagePath);
		    }
	    }
		// 商品データ更新
		try {
		    int retVal = productService.updateProduct(product);
		    if (retVal == 0) {
	    	    // 「当該商品データは他ユーザにより更新されています。データを再度ご確認ください。」と表示。
	    	    message =  msg.getMessage("UPDERR1", null, locale);
	        }
		} catch (Exception e) {
			// 更新エラーメッセージを設定
    	    message =  msg.getMessage("UPDERR2", null, locale);
		}	
		// メッセージをリダイレクトアトリビュートに設定
    	redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/product-list";	
	}
}
