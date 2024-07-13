package ims.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import entity.ProductBuilder;
import ims.entity.Product;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ValidationTest {
	private Product product;
	private ProductBuilder builder;
	private BindingResult bindingResult;

	private final Validator validator =
			Validation.buildDefaultValidatorFactory().getValidator();
	
	@BeforeEach
	public void getDefaultProduct() {
		builder = new ProductBuilder();
		product = builder.buildProduct();
	}

	@Test
	@Disabled
	public void test_noErrors() throws Exception {
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		assertNull(bindingResult.getFieldError());
		assertEquals(violations.size(), 0);
	}
	
	@Test
	@Disabled
	public void test_blankNameCausesError() throws Exception {
		product.setName("");
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		violations.forEach(action -> {
			assertThat(action.getPropertyPath().toString()).isEqualTo("name");
			assertThat(action.getMessage()).isEqualTo("must not be blank");
		});
	}
	
	@Test
	@Disabled
	public void test_nameSize40IsOk() throws Exception {
		// make string with 40 chars
		String str = new String(new char[40]).replace("\0", "a");
		product.setName(str);
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		assertNull(bindingResult.getFieldError());
		assertEquals(violations.size(), 0);	
	}
	
	@Test
	@Disabled
	public void test_nameSize41CausesError() throws Exception {
		// make string with 41 chars
		String str = new String(new char[41]).replace("\0", "a");
		product.setName(str);
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		violations.forEach(action -> {
			assertThat(action.getPropertyPath().toString()).isEqualTo("name");
			assertThat(action.getMessage()).isEqualTo("must not exceed 40 characters");
		});
	}
}
