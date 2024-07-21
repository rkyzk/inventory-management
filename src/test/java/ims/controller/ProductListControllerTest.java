package ims.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import ims.config.JavaConfig;
import ims.entity.Product;
import ims.entity.ProductBuilder;
import ims.service.ImageUploadService;
import ims.service.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest({ProductListController.class})
@Import({JavaConfig.class})
class ProductListControllerTest {
	@Autowired
    private MockMvc mockmvc;
	
	@Autowired
	private MessageSource msg;
	
	@Value("${aws.endpoint.url}")
	private String endpoint;

	private ProductBuilder builder;
	private Product product1;
	private Product product2;
	private List<Product> list;
	private Locale locale;
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private ImageUploadService imgUploadService;
	
	@BeforeEach
	public void buildDefaultProduct() {
		builder = new ProductBuilder();
		product1 = builder.buildProduct();
		product2 = builder.buildProduct();
		product1.setId(1);
		product1.setName("test001");
		product2.setName("test002");
		list = List.of(product1, product2);
		when(productService.getProductList())
		    .thenReturn(list);
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
		this.mockmvc.perform(get("/product-list"))
    		.andExpect(status().isOk())
    		.andExpect(model().attribute("prodList", equalTo(list)));
	}
	
	@Test
	@Disabled
	void test_itemCountHoldsRightValue() throws Exception {
		this.mockmvc.perform(get("/product-list"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("itemCount", equalTo(2)));		
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
		when(productService.deleteProduct(1))
			.thenReturn(1);
		this.mockmvc.perform(post("/product-list/delete")
	            .param("id", "1"))               
				.andExpect((model().attribute("message",
                    msg.getMessage("DELSUC", null, locale))));
	}
	
	@Test
	@Disabled
	void test_retVal0ShowsDelErrMsg() throws Exception {
		when(productService.deleteProduct(-1))
		    .thenReturn(0);
		this.mockmvc.perform(post("/product-list/delete")
	            .param("id", "-1"))               
				.andExpect((model().attribute("message",
                    msg.getMessage("DELERR", null, locale))));
	}
	
	@Test
	@Disabled
    void test_post_prodListHoldsRightValue() throws Exception {
		this.mockmvc.perform(post("/product-list/delete")
	            .param("id", "-1"))               
				.andExpect(model().attribute("prodList", equalTo(list)));
	}
	
	@Test
	@Disabled
    void test_post_itemCountHoldsRightValue() throws Exception {
		this.mockmvc.perform(post("/product-list/delete")
	            .param("id", "-1"))               
				.andExpect(model().attribute("itemCount", equalTo(2)));
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
		    .andExpect(status().isOk())
        	.andExpect(view().name("product-list"));
	}
}
