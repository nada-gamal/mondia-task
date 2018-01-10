package eg.com.mondia.restapi.service;

import java.math.BigDecimal;
import java.util.List;

import eg.com.mondia.restapi.model.Product;



public interface ProductService {
	
	Product findById(BigDecimal id);
	
	Product findByName(String name);
	
	void saveProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProduct(Product product);

	List<Product> findAllProducts(); 
	
	//void deleteAllProducts();
	
	public boolean isProductExist(Product Product);
	
}
