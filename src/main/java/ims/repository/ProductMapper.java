package ims.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ims.entity.Product;

@Mapper
public interface ProductMapper {
	/** 商品登録 */
	public int insertProduct(Product product);
	/** 商品リスト取得 */
	public List<Product> getProductList();
	/**
	 * 商品データ取得
	 * @param id
	 */
	public Product getProduct(int id);	
	/**
	 * 商品データ更新
	 * @param product
	 */
	public int updateProduct(Product product);
	/**
	 * 商品データ削除
	 * @param int
	 */
	public int deleteProduct(int id);
}