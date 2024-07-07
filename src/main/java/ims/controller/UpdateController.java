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
import org.springframework.web.bind.annotation.PostMapping;
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
	 * Get product data from DB using the given id
	 * and display product update page
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
	 * Validate user input,
	 * add/remove image to/from S3 bucket,
	 * update product data in DB.
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
	@PostMapping("/product-update")
	public String postUpdate(Model model, Locale locale,
			@RequestParam(name="curr-img", required=false) boolean currImg,
			RedirectAttributes redirectAttributes,
			@ModelAttribute @Valid Product product,
			BindingResult bindingResult) throws IOException {
		if (bindingResult.hasErrors()) {
			return "product-update";
		}
		/** If image has been removed,
		 *  delete the image from AWS storage
		 *  and set imagePath & imageName null
		 */
		if (currImg) {
			imgUploadService.deleteImg(product.getImagePath());
			product.setImagePath(null);
			product.setImageName(null);
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
		// update product
		int retVal = productService.updateProduct(product);
		String message;
		if (retVal == 1) {
	    	// set success message
	    	message =  msg.getMessage("UPDSUC", null, locale);
	    } else {
	    	// set error message
	    	message =  msg.getMessage("UPDERR", null, locale);
	    }
		// set the message to be displayed on the list page
    	redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/product-list";	
	}
}
