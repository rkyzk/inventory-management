package ims.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
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

import ims.entity.Product;
import ims.entity.ProductBuilder;
import ims.service.ProductService;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProductListControllerTest {
	@Autowired
    private MockMvc mockmvc;
	
	@Autowired
	private MessageSource msg;
	
	@Value("${aws.endpoint.url}")
	private String endpoint;

	private static ProductBuilder builder;
	private static Product product;
	private Locale locale;
	
	@Autowired
	private ProductService productService;
	
	@BeforeAll
	public static void buildDefaultProduct() {
		builder = new ProductBuilder();
		product = builder.buildProduct();
	}

	@Test
	@Disabled
	void test_getProductListPage() throws Exception {
		this.mockmvc.perform(get("/product-list"))
        	.andExpect(status().isOk())
        	.andExpect(view().name("product-list"));
	}
	
	@Test
	@Disabled
    void test_prodListHoldsRightValue() throws Exception {
		List<Product> list = productService.getProductList();
		this.mockmvc.perform(get("/product-list"))
    		.andExpect(status().isOk())
    		.andExpect(model().attribute("prodList", equalTo(list)));
	}
	
	@Test
	@Disabled
	void test_itemCountHoldsRightValue() throws Exception {
		List<Product> list = productService.getProductList();
		this.mockmvc.perform(get("/product-list"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("itemCount", equalTo(list.size())));		
	}
	
	@Test
	@Disabled
	void test_endpointHoldsRightValue() throws Exception {
		this.mockmvc.perform(get("/product-list"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("awsUrl", equalTo(endpoint)));		
	}
	
	@Test
	@Disabled
    void test_retVal1ShowsDelSuccessMsg() throws Exception {
		// insert a product and get the id
		int code = productService.insertProduct(product);
		String id = String.valueOf(code);
		this.mockmvc.perform(post("/product-list/delete")
	            .param("id", id))               
				.andExpect((model().attribute("message",
                    msg.getMessage("DELSUC", null, locale))));
	}
	
	@Test
	@Disabled
    void test_retVal0ShowsDelErrMsg() throws Exception {
		this.mockmvc.perform(post("/product-list/delete")
	            .param("id", "-1"))               
				.andExpect((model().attribute("message",
                    msg.getMessage("DELERR", null, locale))));
	}
	
	@Test
	@Disabled
    void test_post_prodListHoldsRightValue() throws Exception {
		List<Product> list = productService.getProductList();
		this.mockmvc.perform(post("/product-list/delete")
	            .param("id", "-1"))               
				.andExpect(model().attribute("prodList", equalTo(list)));
	}
	
	@Test
	@Disabled
    void test_post_itemCountHoldsRightValue() throws Exception {
		List<Product> list = productService.getProductList();
		this.mockmvc.perform(post("/product-list/delete")
	            .param("id", "-1"))               
				.andExpect(model().attribute("itemCount", equalTo(list.size())));
	}
	
	@Test
	@Disabled
    void test_post_awsUrlHoldsRightValue() throws Exception {
		this.mockmvc.perform(post("/product-list/delete")
	            .param("id", "-1"))               
				.andExpect(model().attribute("awsUrl", equalTo(endpoint)));
	}
	
	@Test
	@Disabled
	void test_post_showsProductListPage() throws Exception {
		this.mockmvc.perform(post("/product-list/delete")
		    .param("id", "-1"))
        	.andExpect(view().name("product-list"));
	}
}
