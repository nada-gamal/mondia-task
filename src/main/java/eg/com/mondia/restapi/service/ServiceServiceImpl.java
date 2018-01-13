package eg.com.mondia.restapi.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eg.com.mondia.restapi.dao.ServiceDao;
//import eg.com.mondia.restapi.model.Service;
import eg.com.mondia.restapi.model.LoOperator;
import eg.com.mondia.restapi.model.Product;

@Service("serviceService")
@Transactional
public class ServiceServiceImpl implements ServiceService{
	
	@Autowired
	ServiceDao serviceDao;
	
	@Autowired
	LoOperatorService loOperatorService;
	
	@Autowired
	ProductService productService;
	

	public List<eg.com.mondia.restapi.model.Service> findAllServices() {
		return serviceDao.findAll();
	}
	
	public eg.com.mondia.restapi.model.Service findById(BigDecimal id) {
		return serviceDao.findById(id);
	}
	
	public eg.com.mondia.restapi.model.Service findByName(String name) {
		return serviceDao.findByName(name);
	}
	
	public String saveService(eg.com.mondia.restapi.dto.ServiceDto serviceDto) {
		LoOperator op = loOperatorService.findById(serviceDto.getOperatorId());
		if(op.getPackageId() == 'M'){
			if(serviceDto.getOperatorPackageId() == null || serviceDto.getOperatorPackageId().equals(BigDecimal.ZERO)){
				return new String("ERR: Mandatory Package Id");
			}
		}
		if(op.getServiceId() == 'M'){
			if(serviceDto.getOperatorServiceId() == null || serviceDto.getOperatorServiceId().equals(BigDecimal.ZERO)){
				return new String("ERR: Mandatory Operator Service Id");
			}
		}
		eg.com.mondia.restapi.model.Service service = new eg.com.mondia.restapi.model.Service();
		service.setName(serviceDto.getName());
		service.setType(serviceDto.getType());
		service.setOperatorPackageId(serviceDto.getOperatorPackageId());
		service.setOperatorServiceId(serviceDto.getOperatorServiceId());
		service.setLoOperator(op);
		
		Product p = productService.findById(serviceDto.getProductId());
		service.setProduct(p);
		
		serviceDao.save(service);
		
		return service.getId().toString();
	}

	public void updateService(eg.com.mondia.restapi.model.Service service) {
		serviceDao.update(service);
	}

	public void deleteService(eg.com.mondia.restapi.model.Service service) {
		serviceDao.delete(service);
	}

	public boolean isServiceExist(eg.com.mondia.restapi.dto.ServiceDto serviceDto) {
		return findByName(serviceDto.getName())!=null;
	}

}
