package eg.com.mondia.restapi.dao;

import java.math.BigDecimal;
import java.util.List;

import eg.com.mondia.restapi.model.Service;

public interface ServiceDao {

	 	public void save(Service service);
	 	
	 	public void update(Service service);
	 	
	 	public Service findById(BigDecimal id);
	 	
	 	public Service findByName(String name);
	 	
	 	public void delete(Service service);
	 	
	 	public List<Service> findAll();
	 	
	 	//public void deleteAll();
	 	
}
