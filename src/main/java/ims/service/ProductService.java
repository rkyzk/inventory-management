package ims.service;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	/** product list */
	final private List<Product> products = productMapper.getProductList();
	
	/**
	 * return page with product list
	 * 
	 * @param pageable
	 * @return page
	 */
	public Page<Product> getProductPage(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> prodList;
        if (products.size() < startItem) {
            prodList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            prodList = products.subList(startItem, toIndex);
        }
        Page<Product> productPage
          = new PageImpl<Product>(prodList,
        		  PageRequest.of(currentPage, pageSize), products.size());
        return productPage;
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
		if (retCode == 1) return priceStockMapper.updateProductData(product);
		else return 0;
	}
	
	/**
	 * delete product.
	 * 
	 * @param id
	 * @return return code
	 */
	@Transactional
	public int deleteProduct(int id) {
		int retCode = priceStockMapper.deleteProductData(id);
		if (retCode == 1) return productMapper.deleteProduct(id);
		else return 0;		
	}
}
