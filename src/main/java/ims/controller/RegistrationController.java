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

@Controller
public class RegistrationController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ImageUploadService imgUploadService;
	
	@Autowired
	MessageSource msg;

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
	 * Validates user input,
	 * upload images to s3 bucket,
	 * and insert product data in DB.
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
		// if there're validation errors, display register product page again.
		if (bindingResult.hasErrors()) {
			return "product-registration";
		}
		// if image has been added, upload it to S3 bucket
		if(product.getMultipartFile() != null && !product.getMultipartFile().isEmpty()) {
			String imageName = product.getMultipartFile().getOriginalFilename();
			product.setImageName(imageName);
			String categoryName = CategoryEnum.getValueByCode(product.getCategoryId()).getCategory();
			String imagePath = imgUploadService.uploadImg(
					product.getMultipartFile(),
					categoryName,
					imageName);		
			product.setImagePath(imagePath);
		}
		// insert product in DB
	    productService.insertProduct(product);
	    // send success message to the list controller
		redirectAttributes.addFlashAttribute("message", msg.getMessage("REGSUC", null, locale));
		return "redirect:/product-list";
	}
}
