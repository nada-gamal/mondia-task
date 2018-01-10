/*package eg.com.mondia.restapi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eg.com.mondia.restapi.model.Product;;

@Service("productService")
@Transactional
public class ProductServiceImpl_bk implements ProductService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Product> products;
	
	static{
		products= populateDummyProducts();
	}

	public List<Product> findAllProducts() {
		return products;
	}
	
	public Product findById(long id) {
		for(Product product : products){
			if(product.getId() == id){
				return product;
			}
		}
		return null;
	}
	
	public Product findByName(String name) {
		for(Product product : products){
			if(product.getName().equalsIgnoreCase(name)){
				return product;
			}
		}
		return null;
	}
	
	public void saveProduct(Product product) {
		product.setId((int) counter.incrementAndGet());
		products.add(product);
	}

	public void updateProduct(Product product) {
		int index = products.indexOf(product);
		products.set(index, product);
	}

	public void deleteProductById(long id) {
		
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext(); ) {
		    Product Product = iterator.next();
		    if (Product.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isProductExist(Product product) {
		return findByName(product.getName())!=null;
	}

	private static List<Product> populateDummyProducts(){
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(counter.incrementAndGet(),"P1","Desc1",30.0, 700.0));
		products.add(new Product(counter.incrementAndGet(),"P2","Desc2",60.0, 90.0));
		
		return products;
	}

	public void deleteAllProducts() {
		products.clear();
	}

}
*/