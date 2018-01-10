package eg.com.mondia.restapi.dao;

import java.math.BigDecimal;
import java.util.List;

import eg.com.mondia.restapi.model.Product;

public interface ProductDao {

	 	public void save(Product p);
	 	
	 	public void update(Product p);
	 	
	 	public Product findById(BigDecimal id);
	 	
	 	public Product findByName(String name);
	 	
	 	public void delete(Product p);
	 	
	 	public List<Product> findAll();
	 	
	 	//public void deleteAll();
	 	
}
