package ims.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import entity.ProductBuilder;
import ims.entity.Product;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProductRegistrationControllerTest {
	@Autowired
    private MockMvc mockmvc;

	private ProductBuilder builder;
	private Product product;
	
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
    void test_validationErrorsReturnsRegistrationPage() throws Exception {
		product.setName("");
		this.mockmvc.perform(post("/product-registration").flashAttr("product", product))
				.andExpect(model().hasErrors())
				.andExpect(view().name("product-registration"));
	}
}
