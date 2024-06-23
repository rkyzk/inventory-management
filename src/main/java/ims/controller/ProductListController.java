package ims.controller;

import java.util.Locale;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ProductListController {
	
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
		return "product-list";
	}

}
