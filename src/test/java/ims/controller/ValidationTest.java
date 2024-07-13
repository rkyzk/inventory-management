package ims.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
	
	@ParameterizedTest
	@ValueSource(ints = {1, 9999})
	@Disabled
    void test_quantity1to9999IsOk(int input) throws Exception {
		product.setQuantity(input);
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		assertNull(bindingResult.getFieldError());
		assertEquals(violations.size(), 0);	
	}
	
	@Test
	@Disabled
    void test_quantity0CausesError() throws Exception {
		product.setQuantity(0);
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		violations.forEach(action -> {
			assertThat(action.getPropertyPath().toString()).isEqualTo("quantity");
			assertThat(action.getMessage())
				.isEqualTo("must be greater than or equal to 1");
		});
	}

	@Test
	@Disabled
    void test_priceNullCausesError() throws Exception {
		product.setPrice(null);
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		violations.forEach(action -> {
			assertThat(action.getPropertyPath().toString()).isEqualTo("price");
			assertThat(action.getMessage())
				.isEqualTo("must not be blank");
		});
	}
	
	@Test
	@Disabled
    void test_priceMin0IsOk() throws Exception {
		product.setPrice(new BigDecimal("0.00"));
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		assertNull(bindingResult.getFieldError());
		assertEquals(violations.size(), 0);	
	}
	
	@Test
	@Disabled
    void test_priceNegativeValCausesError() throws Exception {
		product.setPrice(new BigDecimal("-0.50"));
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		violations.forEach(action -> {
			assertThat(action.getPropertyPath().toString()).isEqualTo("price");
			assertThat(action.getMessage().toString())
			    .isEqualTo("must be greater than or equal to 0.00");
		});	
	}
	
	@Test
	@Disabled
    void test_priceDigitsInt5Frac2AreOk() throws Exception {
		product.setPrice(new BigDecimal("12345.01"));
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		assertNull(bindingResult.getFieldError());
		assertEquals(violations.size(), 0);	
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"123456", "1.123"})
	@Disabled
    void test_priceDigitsInt6AndFrac3_failValidation(String input) throws Exception {
		product.setPrice(new BigDecimal(input));
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		violations.forEach(action -> {
			assertThat(action.getPropertyPath().toString()).isEqualTo("price");
			assertThat(action.getMessage().toString())
			    .isEqualTo("numeric value out of bounds (<5 digits>.<2 digits> expected)");
		});
    }
	
	@ParameterizedTest
	@ValueSource(ints = {0, 99999})
	@Disabled
    void test_stock0And99999AreOk() throws Exception {
		product.setStock(0);
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		assertNull(bindingResult.getFieldError());
		assertEquals(violations.size(), 0);	
	}
	
	@Test
	@Disabled
    void test_stockNegative1_failValidation() throws Exception {
		product.setStock(-1);
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		violations.forEach(action -> {
			assertThat(action.getPropertyPath().toString()).isEqualTo("stock");
			assertThat(action.getMessage())
				.isEqualTo("must be greater than or equal to 0");
		});
    }
	
	@Test
	@Disabled
    void test_stock100000_failValidation() throws Exception {
		product.setStock(100000);
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		violations.forEach(action -> {
			assertThat(action.getPropertyPath().toString()).isEqualTo("stock");
			assertThat(action.getMessage())
				.isEqualTo("must be less than or equal to 99999");
		});
    }
	
	@Test
	@Disabled
    void test_description_200CharsAreOk() throws Exception {
		String str = new String(new char[200]).replace("\0", "a");
		product.setDescription(str);
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		assertNull(bindingResult.getFieldError());
		assertEquals(violations.size(), 0);	
	}
	
	@Test
	@Disabled
    void test_descriptionAbove200Chars_failsValidation() throws Exception {
		String str = new String(new char[201]).replace("\0", "a");
		product.setDescription(str);
		bindingResult = new BindException(product, "product");
		Set<ConstraintViolation<Product>> violations =
				validator.validate(product);
		violations.forEach(action -> {
			assertThat(action.getPropertyPath().toString()).isEqualTo("description");
			assertThat(action.getMessage().toString())
			    .isEqualTo("must not exceed 200 characters");
		});
	}
}
