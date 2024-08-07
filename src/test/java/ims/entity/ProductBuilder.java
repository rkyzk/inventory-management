package ims.entity;

import java.math.BigDecimal;

import ims.entity.Product;

public class ProductBuilder {

	/** product */
	private Product product = new Product();

	/**
	 * 
	 * Return product object for tests.
	 * 
	 * @return product
	 */
	public Product buildProduct() {
		this.product.setName("test");
		this.product.setCategoryId("0");
		this.product.setQuantity(3);
		this.product.setStock(5);
		this.product.setPrice(new BigDecimal("10.55"));
		return this.product;	
	}
}
