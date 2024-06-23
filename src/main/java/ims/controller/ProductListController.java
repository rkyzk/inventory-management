package ims.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ims.entity.Product;
import ims.service.ProductService;

@Controller
public class ProductListController {

	@Autowired
	ProductService productService;
	
	@Value("${aws.endpoint.url}")
	private String endpoint;
	
	/**
	 * Display product list page.
	 *
	 * @param model
	 * @param locale
	 * @param redirectAttributes
	 * @return product list page
	 */
	@GetMapping("/product-list")
	public String getProductList(Model model, Locale locale,
			RedirectAttributes redirectAttributes) {	
		List<Product> prodList = productService.getProductList();
		model.addAttribute("prodList", prodList);
		model.addAttribute("itemCount", prodList.size());
		model.addAttribute("awsUrl", endpoint);
		return "product-list";
	}

}
