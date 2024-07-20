package ims.repository;

import org.apache.ibatis.annotations.Mapper;

import ims.entity.Product;

@Mapper
public interface PriceStockMapper {
	/** insert product data */
	public int insertProductData(Product product);
	/** update product data */
	public int updateProductData(Product product);
	/** delete product data */
	public int deleteProductData(int id);
}
