package ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ims.entity.Product;
import ims.service.ProductService;

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
}
