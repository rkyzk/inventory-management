package ims.entity;

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
		this.product.setCategoryId(0);
		this.product.setColorId(0);
		this.product.setStock(5);
		this.product.setPrice(1200);
		return this.product;	
	}
}
