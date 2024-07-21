package ims.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ims.config.JavaConfig;
import ims.entity.Product;
import ims.entity.ProductBuilder;
import ims.service.ImageUploadService;
import ims.service.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest({UpdateController.class})
@Import({JavaConfig.class})
class UpdateControllerTest {
	@Autowired
    private MockMvc mockmvc;
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private ImageUploadService imgUploadService;
    
	@Autowired
    MessageSource msg;

	private ProductBuilder builder;
	private Product productOld;
	private Product productNew;
	private Locale locale;
	
	@Value("${aws.endpoint.url}")
	private String endpoint;
	
	@BeforeEach
	public void buildDefaultProduct() {
		builder = new ProductBuilder();
		productNew = builder.buildProduct();
		productOld = builder.buildProduct();
		productOld.setId(1);
		productNew.setName("newProduct");
		when(productService.getProduct(1)).thenReturn(productOld);
	}
	
	@Test
	void test_getUpdate() throws Exception {
		this.mockmvc.perform(get("/product-update")
				.param("id", "1"))
        	.andExpect(status().isOk())
        	.andExpect(view().name("product-update"));
	}
	
	@Test
	void test_attrProductHoldsRightValue() throws Exception {
		this.mockmvc.perform(get("/product-update")
				.param("id", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("product", equalTo(productOld)));
	}
	
	@Test
	void test_awsUrlHoldsRightValue() throws Exception {
		this.mockmvc.perform(get("/product-update")
				.param("id", "1"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("awsUrl", equalTo(endpoint)));		
	}
	
	@Test
    void test_validationErrorsReturnUpdatePage() throws Exception {
		productNew.setName("");
		this.mockmvc.perform(post("/product-update")
				.flashAttr("product", productNew))
				.andExpect(model().hasErrors())
				.andExpect(view().name("product-update"));
	}
	
	@Test
	void test_updateSuccessMessage() throws Exception {
		when(productService.updateProduct(productNew)).thenReturn(1);
		this.mockmvc.perform(post("/product-update").flashAttr("product", productNew))
				.andExpect(MockMvcResultMatchers.flash().attribute("message",
						msg.getMessage("UPDSUC", null, locale)))
				.andExpect(redirectedUrl("/product-list"));			
	}
	
	@Test
	void test_updateErrorMessage() throws Exception {
		when(productService.updateProduct(productNew)).thenReturn(0);
		this.mockmvc.perform(post("/product-update").flashAttr("product", productNew))
				.andExpect(MockMvcResultMatchers.flash().attribute("message",
						msg.getMessage("UPDERR", null, locale)))
				.andExpect(redirectedUrl("/product-list"));			
	}
	
	@Test
    void test_redirectedToProductList() throws Exception {
		this.mockmvc.perform(post("/product-update")
				.flashAttr("product", productNew))
				.andExpect(redirectedUrl("/product-list"));			
	}
}
