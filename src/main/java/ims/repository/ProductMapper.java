package ims.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ims.entity.Product;

@Mapper
public interface ProductMapper {
	/** insert product */
	public void insertProduct(Product product);
	/** get product list */
	public List<Product> getProductList();
}