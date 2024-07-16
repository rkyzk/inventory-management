package ims.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ims.entity.Product;
import ims.entity.ProductBuilder;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProductRegistrationControllerTest {
	@Autowired
    private MockMvc mockmvc;
	
	@Autowired
	private MessageSource msg;

	private ProductBuilder builder;
	private Product product;
	private Locale locale;
	
	@BeforeEach
	public void getDefaultProduct() {
		builder = new ProductBuilder();
		product = builder.buildProduct();
	}

	@Test
	@Disabled
	void test_getRegistrationPage() throws Exception {
		this.mockmvc.perform(get("/product-registration"))
        	.andExpect(status().isOk())
        	.andExpect(view().name("product-registration"));
	}
	
	@Test
	@Disabled
    void test_validationErrorsReturnsRegistrationPage() throws Exception {
		product.setName("");
		this.mockmvc.perform(post("/product-registration").flashAttr("product", product))
				.andExpect(model().hasErrors())
				.andExpect(view().name("product-registration"));
	}
	
	@Test
	@Disabled
	void test_registerSuccessMessage() throws Exception {
		this.mockmvc.perform(post("/product-registration").flashAttr("product", product))
				.andExpect(MockMvcResultMatchers.flash().attribute("message",
						msg.getMessage("REGSUC", null, locale)))
				.andExpect(redirectedUrl("/product-list"));			
	}
	
	@Test
	@Disabled
    void test_redirectedToProductList() throws Exception {
		this.mockmvc.perform(post("/product-registration")
				.flashAttr("product", product))
				.andExpect(model().hasNoErrors())
				.andExpect(redirectedUrl("/product-list"));			
	}
}
