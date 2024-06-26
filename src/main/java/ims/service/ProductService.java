package ims.service;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ims.entity.Product;
import ims.repository.PriceStockMapper;
import ims.repository.ProductMapper;

/**
 * Execute DB transactions.
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private PriceStockMapper priceStockMapper;
	
	/**
	 * Insert product.
	 * 
	 * @param product
	 */
	@Transactional
	@Options(useGeneratedKeys=true)
	public void insertProduct(Product product) {
		productMapper.insertProduct(product);
		priceStockMapper.insertProductData(product);
	}
	
	/**
	 * Get product list.
	 * 
	 * @return product list
	 */
	public List<Product> getProductList() {
		return productMapper.getProductList();
	}
	
	/**
	 * get product.
	 * 
	 * @param product
	 */
	public Product getProduct(int id) {
		return productMapper.getProduct(id);
	}
	
	/**
	 * update product.
	 * 
	 * @param id
	 * @return return code
	 */
	@Transactional
	public int updateProduct(Product product) {
		int retCode = productMapper.updateProduct(product);
		int retCode2 = priceStockMapper.updateProductData(product);
		if (retCode == 1 && retCode2 ==1) return 1; else return 0;		
	}
}
