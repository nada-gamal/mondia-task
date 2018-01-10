package eg.com.mondia.restapi.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import eg.com.mondia.restapi.model.Product;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<BigDecimal, Product> implements ProductDao{
	
	 	public void save(Product p) {
	 		persist(p);
	 	}

	 	public void update(Product p) {
	 		update(p);
	 	}

	 	public Product findById(BigDecimal id) {
	 		Product p = (Product) getByKey(id);
	 		return p; 
	 	}

	 	public void delete(Product p) {
	 		delete(p);
	 	}

	 	public List<Product> findAll() {
	 		@SuppressWarnings("unchecked")
			List<Product> products = (List<Product>) getEntityManager().createQuery("from Product").getResultList();
	 		return products;
	 	}

		@Override
		public Product findByName(String name) {
			// TODO Auto-generated method stub
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
}