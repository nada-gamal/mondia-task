package eg.com.mondia.restapi.service;

import java.math.BigDecimal;
import java.util.List;

import eg.com.mondia.restapi.model.Product;



public interface ProductService {
	
	public Product findById(BigDecimal id);
	
	public Product findByIdWithService(BigDecimal id);
	
	public Product findByName(String name);
	
	public void saveProduct(Product product);
	
	public boolean updateProduct(Product product);
	
	public boolean deleteProduct(BigDecimal id);

	public List<Product> findAllProducts(); 
	
	public boolean isProductExist(Product Product);
	
}
