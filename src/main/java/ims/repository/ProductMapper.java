package ims.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ims.entity.Product;

@Mapper
public interface ProductMapper {
	/** insert product */
	public int insertProduct(Product product);
	/** get product list */
	public List<Product> getProductList();
	/** get product 
	 * @param id */
	public Product getProduct(int id);	
	/** update product */
	public int updateProduct(Product product);
	/** delete product */
	public int deleteProduct(int id);
}