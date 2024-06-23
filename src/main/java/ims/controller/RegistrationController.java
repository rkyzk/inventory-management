package ims.controller;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ims.entity.Product;
import ims.service.ProductService;

@Controller
public class RegistrationController {
	
	@Autowired
	ProductService productService;

	/**
	 * Display product registration page.
	 *
	 * @param  model
	 * @param  product
	 * @return product registration page
	 */
	@GetMapping("/product-registration")
	public String getProductRegistration(Model model,
			@ModelAttribute("product") Product product) {
		return "product-registration";
	}
	
	/**
	 * Insert product data in DB.
	 *
	 * @param model
	 * @param redirectAttributes
	 * @param product
	 * @param bindingResult
	 */
	@PostMapping("/product-registration")
	public String postProduct(Model model, Locale locale,
			RedirectAttributes redirectAttributes,
		    @ModelAttribute("product") Product product) throws IOException {
		// insert product in DB
		productService.insertProduct(product);
		// redirect to product list page.
		return "redirect:/product-list";
	}
}
