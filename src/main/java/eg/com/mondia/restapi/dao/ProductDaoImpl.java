package eg.com.mondia.restapi.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import eg.com.mondia.restapi.model.Product;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<BigDecimal, Product> implements ProductDao{
	
	 	public void save(Product p) {
	 		persist(p);
	 	}

	 	public boolean updateProduct(Product p) {
	 		Product currentProduct = (Product) getByKey(p.getId());
			
			if (currentProduct==null) {
				//System.out.println("Product with id " + id + " not found");
				return false;
			}

			currentProduct.setName(p.getName());
			currentProduct.setDescription(p.getDescription());
			currentProduct.setMinPrice(p.getMinPrice());
			currentProduct.setMaxPrice(p.getMaxPrice());

	 		
	 		update(currentProduct);
	 		return true;
	 	}

	 	public Product findById(BigDecimal id) {
	 		Product p = (Product) getByKey(id);
	 		return p; 
	 	}

	 	public boolean delete(BigDecimal id) {
	 		Product p = (Product) getByKey(id);
	 		if (p != null){
	 			delete(p);
	 			return true;
	 		}
	 		else
	 			return false;
	 	}

	 	public List<Product> findAll() {
	 		@SuppressWarnings("unchecked")
			List<Product> products = (List<Product>) getEntityManager().createQuery("from Product").getResultList();
	 		return products;
	 	}

		@Override
		public Product findByName(String name) {
			try{
	            Product product = (Product) getEntityManager()
	                    .createQuery("SELECT p FROM Product p WHERE p.name LIKE :productName")
	                    .setParameter("productName", name)
	                    .getSingleResult();
	             
	            if(product!=null){
	            	return product;
	            }
	             
	        }catch(NoResultException ex){
	            return null;
	        }
			return null;
		}

		@Override
		public Product findByIdWithService(BigDecimal id) {
			try{
	            Product product = (Product) getEntityManager()
	                    .createQuery("SELECT p FROM Product p JOIN FETCH p.services WHERE p.id = :productId")
	                    .setParameter("productId", id)
	                    .getSingleResult();
	             
	            if(product!=null){
	            	product.setServices(product.getServices());
	            	return product;
	            }
	             
	        }catch(NoResultException ex){
	            return null;
	        }
			return null;
		}
}