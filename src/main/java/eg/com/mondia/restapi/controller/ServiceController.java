package eg.com.mondia.restapi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import eg.com.mondia.restapi.dto.ServiceDto;
import eg.com.mondia.restapi.model.LoOperator;
import eg.com.mondia.restapi.model.Service;
import eg.com.mondia.restapi.service.LoOperatorService;
import eg.com.mondia.restapi.service.ServiceService;

@RestController
public class ServiceController {

	@Autowired
	ServiceService serviceService;  //Service which will do all data retrieval/manipulation work

	
	//-------------------Retrieve All Services--------------------------------------------------------
	
	@RequestMapping(value = "/service/", method = RequestMethod.GET)
	public ResponseEntity<List<Service>> listAllServices() {
		List<Service> services = serviceService.findAllServices();
		if(services.isEmpty()){
			return new ResponseEntity<List<Service>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Service>>(services, HttpStatus.OK);
	}


	//-------------------Retrieve Single Service--------------------------------------------------------
	
	@RequestMapping(value = "/service/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Service> getService(@PathVariable("id") long id) {
		System.out.println("Fetching Service with id " + id);
		Service service = serviceService.findById(BigDecimal.valueOf(id));
		if (service == null) {
			System.out.println("Service with id " + id + " not found");
			return new ResponseEntity<Service>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Service>(service, HttpStatus.OK);
	}

	
	
	//-------------------Create a Service--------------------------------------------------------
	
	@RequestMapping(value = "/service/", method = RequestMethod.POST)
	public ResponseEntity createService(@RequestBody ServiceDto serviceDto, 	UriComponentsBuilder ucBuilder) {
		

		if (serviceService.isServiceExist(serviceDto)) {
			System.out.println("A Service with name " + serviceDto.getName() + " already exist");
			return new ResponseEntity<String>("A Service with name " + serviceDto.getName() + " already exist",HttpStatus.CONFLICT);
		}
		//System.out.println("Creating Service " + service.toString());
		String response = serviceService.saveService(serviceDto);

		if(response.startsWith("ERR:")){
			System.out.println(response);
			return new ResponseEntity<String>(response,HttpStatus.PARTIAL_CONTENT);
		}else{
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/service/{id}").buildAndExpand(response).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		
	}

	
	//------------------- Update a Service --------------------------------------------------------
	
	@RequestMapping(value = "/service/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Service> updateService(@PathVariable("id") long id, @RequestBody Service service) {
		System.out.println("Updating Service " + id);
		
		Service currentService = serviceService.findById(BigDecimal.valueOf(id));
		
		if (currentService==null) {
			System.out.println("Service with id " + id + " not found");
			return new ResponseEntity<Service>(HttpStatus.NOT_FOUND);
		}

		currentService.setName(service.getName());
		//currentService.setCountry(service.getCountry());
		//currentService.setPackageId(service.getPackageId());
		//currentService.setServiceId(service.getServiceId());
		
		serviceService.updateService(currentService);
		return new ResponseEntity<Service>(currentService, HttpStatus.OK);
	}

	//------------------- Delete a Service --------------------------------------------------------
	
	@RequestMapping(value = "/service/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Service> deleteService(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Service with id " + id);

		Service service = serviceService.findById(BigDecimal.valueOf(id));
		if (service == null) {
			System.out.println("Unable to delete. Service with id " + id + " not found");
			return new ResponseEntity<Service>(HttpStatus.NOT_FOUND);
		}

		serviceService.deleteService(service);
		return new ResponseEntity<Service>(HttpStatus.NO_CONTENT);
	}

	
	//------------------- Delete All Service --------------------------------------------------------
	
	/*@RequestMapping(value = "/service/", method = RequestMethod.DELETE)
	public ResponseEntity<Service> deleteAllServices() {
		System.out.println("Deleting All Services");

		serviceService.deleteAllServices();
		return new ResponseEntity<Service>(HttpStatus.NO_CONTENT);
	}
*/
}
