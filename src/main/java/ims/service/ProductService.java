package ims.service;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ims.entity.Product;
import ims.repository.ProductMapper;

/**
 * DB処理操作を行うサービスクラス.
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	/**
	 * 商品データを挿入する.
	 * 
	 * @param product
	 */
	@Transactional
	@Options(useGeneratedKeys=true)
	public int insertProduct(Product product) {
		int code = productMapper.insertProduct(product);
		if (code > 0) return product.getId();
		else return 0;
	}
	
	/**
	 * 商品リストを取得する.
	 * 
	 * @return 商品リスト
	 */
	public List<Product> getProductList() {
		return productMapper.getProductList();
	}
	
	/**
	 * 商品データを取得する.
	 * 
	 * @param product
	 */
	public Product getProduct(int id) {
		return productMapper.getProduct(id);
	}
	
	/**
	 * 商品データを更新する.
	 * 
	 * @param id
	 * @return リターンコード
	 */
	@Transactional
	public int updateProduct(Product product) {
		int code = productMapper.updateProduct(product);
		return code;
	}
	
	/**
	 * 商品データを削除する.
	 * 
	 * @param id
	 * @return リターンコード
	 */
	@Transactional
	public int deleteProduct(int id) {
		int code = productMapper.deleteProduct(id);
		return code;	
	}
}
