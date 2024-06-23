package ims.service;

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
	public void insertProduct(Product product) {
		productMapper.insertProduct(product);
		priceStockMapper.insertProductData(product);
	}

}
