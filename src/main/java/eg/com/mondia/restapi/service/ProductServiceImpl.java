package eg.com.mondia.restapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eg.com.mondia.restapi.dao.ProductDao;
import eg.com.mondia.restapi.model.Product;;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDao productDao;
	
//	private static final AtomicLong counter = new AtomicLong();
//	
//	private static List<Product> products;
	
	/*static{
		products= populateDummyProducts();
	}*/

	public List<Product> findAllProducts() {
		return productDao.findAll();
	}
	
	public Product findById(BigDecimal id) {
		return productDao.findById(id);
	}
	
	public Product findByName(String name) {
		return productDao.findByName(name);
	}
	
	public void saveProduct(Product product) {
		productDao.save(product);
	}

	public boolean updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	public boolean deleteProduct(BigDecimal id) {
		return productDao.delete(id);
	}

	public boolean isProductExist(Product product) {
		return findByName(product.getName())!=null;
	}

	@Override
	public Product findByIdWithService(BigDecimal id) {
		// TODO Auto-generated method stub
		return productDao.findByIdWithService(id);
	}

}
