package eg.com.mondia.restapi.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import eg.com.mondia.restapi.model.Service;

@Repository("serviceDao")
public class ServiceDaoImpl extends AbstractDao<BigDecimal, Service> implements ServiceDao{
	
	 	public void save(Service service) {
	 		persist(service);
	 	}

	 	public void update(Service service) {
	 		update(service);
	 	}

	 	public Service findById(BigDecimal id) {
	 		Service service = (Service) getByKey(id);
	 		return service; 
	 	}

	 	public void delete(Service service) {
	 		delete(service);
	 	}

	 	public List<Service> findAll() {
	 		@SuppressWarnings("unchecked")
			List<Service> services = (List<Service>) getEntityManager().createQuery("from Service").getResultList();
	 		return services;
	 	}

		@Override
		public Service findByName(String name) {
			// TODO Auto-generated method stub
			try{
	            Service service = (Service) getEntityManager()
	                    .createQuery("SELECT s FROM Service s WHERE s.name LIKE :serviceName")
	                    .setParameter("serviceName", name)
	                    .getSingleResult();
	             
	            if(service!=null){
	            	return service;
	            }
	             
	        }catch(NoResultException ex){
	            return null;
	        }
			return null;
		}
}