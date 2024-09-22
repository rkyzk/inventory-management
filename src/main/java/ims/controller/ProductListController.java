package ims.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ims.entity.Product;
import ims.service.ProductService;

@Controller
@RequestMapping("/product-list")
public class ProductListController {

	@Autowired
	ProductService productService;
	
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
	public String getProductList(Model model) {
		List<Product> prodList = productService.getProductList();
		model.addAttribute("prodList", prodList);
		model.addAttribute("itemCount", prodList.size());
		model.addAttribute("awsUrl", endpoint);
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
			RedirectAttributes redirectAttributes,
			@RequestParam("id") int id) {
		int retVal = productService.deleteProduct(id);
		String message;
		if (retVal == 1) {
	    	// 削除成功メッセージを設定
	    	message =  msg.getMessage("DELSUC", null, locale);
	    } else {
	    	// 削除失敗メッセージを設定
	    	message =  msg.getMessage("DELERR", null, locale);
	    }
		List<Product> prodList = productService.getProductList();
		model.addAttribute("prodList", prodList);
		model.addAttribute("itemCount", prodList.size());
		model.addAttribute("awsUrl", endpoint);	
		model.addAttribute("message", message);	
		return "product-list";
	}	
}
