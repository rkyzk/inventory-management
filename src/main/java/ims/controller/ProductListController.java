package ims.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ims.entity.Product;
import ims.service.ImageUploadService;
import ims.service.ProductService;

/**
 * 商品リストページのコントローラークラス
 */
@Controller
@RequestMapping("/product-list")
public class ProductListController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ImageUploadService imgUploadService;
	
	@Value("${aws.endpoint.url}")
	private String endpoint;
	
	@Autowired
	MessageSource msg;
	
	/**
	 * 商品リストページを表示
	 *
	 * @param model
	 * @return 商品リストページ
	 */
	@GetMapping("")
	public String getProductList(Model model, Locale locale) {
		List<Product> prodList;
		String message = null;
		// 削除されていない全商品データを取得
		try {
		    prodList = productService.getProductList();
		} catch (Exception e) {
			// データ取得失敗エラーメッセージを設定
			message = msg.getMessage("GETERR", null, locale);
			model.addAttribute("message", message);
			prodList = null;
		}
		model.addAttribute("prodList", prodList);
		model.addAttribute("itemCount", prodList.size());
		model.addAttribute("awsUrl", endpoint);
		model.addAttribute("unselected", false);
		model.addAttribute("categoryId", 0);
		model.addAttribute("colorId", 0);
		return "product-list";
	}
	
	/**
	 * 商品データを削除.
	 * 
	 * @param model
	 * @param locale
	 * @param redirectAttributes
	 * @param id
	 * @return product list page
	 */
	@PutMapping("/delete")
	public String deleteProduct(Model model, Locale locale,
			@RequestParam("id") int id) {	
		// 画像があるとき、S3 bucketから画像を削除
	    Product product = productService.getProduct(id);	    
	    // 削除成功メッセージを設定（失敗時は先の処理で変更）
	    String message = msg.getMessage("DELSUC", null, locale);
	    if (product.getImageName() != null && product.getImageName().length() != 0) {
	    	boolean deleted = imgUploadService.deleteImg(product.getImageName());
	    	// 画像削除失敗のとき、該当メッセージを設定。
	    	if (!deleted) message = msg.getMessage("DELIMGDELERR", null, locale);
	    };
	    try {
		    productService.deleteProduct(id);
	    } catch (Exception e) {
	    	// 削除失敗メッセージを設定
	    	message =  msg.getMessage("DELERR", null, locale);
	    }
	    // 削除されていない全商品データを取得
		List<Product> prodList = productService.getProductList();
		model.addAttribute("prodList", prodList);
		model.addAttribute("itemCount", prodList.size());
		model.addAttribute("awsUrl", endpoint);	
		model.addAttribute("message", message);
		model.addAttribute("unselected", false);
		model.addAttribute("categoryId", 0);
		model.addAttribute("colorId", 0);
		return "product-list";
	}
	
	/**
	 * 商品データをフィルターする.
	 * @param categoryId
	 *        colorId
	 *        model
	 *        locale
	 * @return 商品リストページ
	 */
	@PostMapping("/filter")
	public String postFilteredList(Model model, Locale locale,
			@RequestParam("category") Integer categoryId,
			@RequestParam("color") Integer colorId) {
		List<Product> prodList;
		boolean unselected = false;
		// カテゴリー、色が指定されなかったときnullを設定
		// (ProductMapper.xmlのSQLのifタグが機能するよう)
        if (categoryId == 0) categoryId = null;
        if (colorId == 0) colorId = null;
		if (categoryId == null && colorId == null) {
			// selected をtrueに設定（「検索条件を選択してください」とメッセージを表示）
            unselected = true;
			// 全件取得
			prodList = productService.getProductList();
		} else {
			// 商品データを抽出して取得
			prodList = productService.getFilteredProductList(categoryId, colorId);
		}
		model.addAttribute("prodList", prodList);
		model.addAttribute("itemCount", prodList.size());
		model.addAttribute("awsUrl", endpoint);
		model.addAttribute("unselected", unselected);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("colorId", colorId);
		return "product-list";
	}
}
