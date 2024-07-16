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
	 * Display product list page.
	 *
	 * @param model
	 * @return product list page
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
	 * Delete product.
	 * 
	 * @param model
	 * @param locale
	 * @param redirectAttributes
	 * @param id
	 * @return product list page
	 */
	@PostMapping("/delete")
	public String deleteProduct(Model model, Locale locale,
			RedirectAttributes redirectAttributes,
			@RequestParam("id") int id) {
		int retVal = productService.deleteProduct(id);
		String message;
		if (retVal == 1) {
	    	// set success message
	    	message =  msg.getMessage("DELSUC", null, locale);
	    } else {
	    	// set error message
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
