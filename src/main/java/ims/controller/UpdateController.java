package ims.controller;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ims.entity.Product;
import ims.service.ProductService;
import jakarta.validation.Valid;

@Controller
public class UpdateController {
	@Autowired
	ProductService productService;
	
	@Value("${aws.endpoint.url}")
	private String endpoint;
	
	@GetMapping("/product-update")
	public String getUpdate(Model model,
			@RequestParam("id") int id) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("awsUrl", endpoint);
		return "product-update";	
	}
	
	@PostMapping("/product-update")
	public String postUpdate(Model model, Locale locale,
			RedirectAttributes redirectAttributes,
			@ModelAttribute @Valid Product product,
			BindingResult bindingResult) throws IOException {
		if (bindingResult.hasErrors()) {
			return "product-update";
		}
		//productService.updateProduct(product);
		
		
		return null;
		
	}
}
