package ims.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ims.entity.Product;
import ims.entity.ProductBuilder;
import ims.service.ProductService;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UpdateControllerTest {
	@Autowired
    private MockMvc mockmvc;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private MessageSource msg;

	private ProductBuilder builder;
	private Product product;
	private Locale locale;
	
	@Value("${aws.endpoint.url}")
	private String endpoint;
	
	@BeforeEach
	public void getDefaultProduct() {
		builder = new ProductBuilder();
		product = builder.buildProduct();
	}

	@Test
	@Disabled
	void test_getUpdate() throws Exception {
		List<Product> list = productService.getProductList();
		String id = String.valueOf(list.get(0).getId());
		this.mockmvc.perform(get("/product-update")
				.param("id", id))
        	.andExpect(status().isOk())
        	.andExpect(view().name("product-update"));
	}
	
	@Test
	@Disabled
	void test_attrProductHoldsRightValue() throws Exception {
		List<Product> list = productService.getProductList();
		String id = String.valueOf(list.get(0).getId());
		Product product = list.get(0);
		this.mockmvc.perform(get("/product-update")
				.param("id", id))
		.andExpect(status().isOk())
		.andExpect(model().attribute("product", equalTo(product)));
	}
	
	@Test
	@Disabled
	void test_awsUrlHoldsRightValue() throws Exception {
		List<Product> list = productService.getProductList();
		String id = String.valueOf(list.get(0).getId());
		this.mockmvc.perform(get("/product-update")
				.param("id", id))
		.andExpect(status().isOk())
		.andExpect(model().attribute("awsUrl", equalTo(endpoint)));		
	}
	
	@Test
	@Disabled
    void test_validationErrorsReturnUpdatePage() throws Exception {
		product.setName("");
		this.mockmvc.perform(post("/product-update")
				.flashAttr("product", product))
				.andExpect(model().hasErrors())
				.andExpect(view().name("product-update"));
	}
	
	@Test
	@Disabled
	void test_updateSuccessMessage() throws Exception {
		List<Product> list = productService.getProductList();
		String id = String.valueOf(list.get(0).getId());
		product.setId(Integer.parseInt(id));
		this.mockmvc.perform(post("/product-update").flashAttr("product", product))
				.andExpect(MockMvcResultMatchers.flash().attribute("message",
						msg.getMessage("UPDSUC", null, locale)))
				.andExpect(redirectedUrl("/product-list"));			
	}
	
	@Test
	@Disabled
	void test_updateErrorMessage() throws Exception {
		// Update operation will fail because id of the product is set to null
		this.mockmvc.perform(post("/product-update").flashAttr("product", product))
				.andExpect(MockMvcResultMatchers.flash().attribute("message",
						msg.getMessage("UPDERR", null, locale)))
				.andExpect(redirectedUrl("/product-list"));			
	}
	
	@Test
	@Disabled
    void test_redirectedToProductList() throws Exception {
		this.mockmvc.perform(post("/product-update")
				.flashAttr("product", product))
				.andExpect(model().hasNoErrors())
				.andExpect(redirectedUrl("/product-list"));			
	}
}
