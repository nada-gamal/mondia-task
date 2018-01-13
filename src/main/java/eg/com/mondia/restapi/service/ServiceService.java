package eg.com.mondia.restapi.service;

import java.math.BigDecimal;
import java.util.List;

import eg.com.mondia.restapi.dto.ServiceDto;
import eg.com.mondia.restapi.model.Service;



public interface ServiceService {
	
	public Service findById(BigDecimal id);
	
	public Service findByName(String name);
	
	public String saveService(ServiceDto serviceDto);
	
	public void updateService(Service service);
	
	public void deleteService(Service service);

	public List<Service> findAllServices(); 
	
	public boolean isServiceExist(ServiceDto serviceDto);
	
}
