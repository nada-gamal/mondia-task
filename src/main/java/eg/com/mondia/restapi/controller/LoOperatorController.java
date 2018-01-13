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

import eg.com.mondia.restapi.model.LoOperator;
import eg.com.mondia.restapi.model.Product;
import eg.com.mondia.restapi.service.LoOperatorService;

@RestController
public class LoOperatorController {

	@Autowired
	LoOperatorService loOperatorService;  //Service which will do all data retrieval/manipulation work

	
	//-------------------Retrieve All LoOperators--------------------------------------------------------
	
	@RequestMapping(value = "/loOperator/", method = RequestMethod.GET)
	public ResponseEntity<List<LoOperator>> listAllLoOperators() {
		List<LoOperator> loOperators = loOperatorService.findAllLoOperators();
		if(loOperators.isEmpty()){
			return new ResponseEntity<List<LoOperator>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<LoOperator>>(loOperators, HttpStatus.OK);
	}


	//-------------------Retrieve Single LoOperator--------------------------------------------------------
	
	@RequestMapping(value = "/loOperator/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoOperator> getLoOperator(@PathVariable("id") long id) {
		System.out.println("Fetching LoOperator with id " + id);
		LoOperator loOperator = loOperatorService.findById(BigDecimal.valueOf(id));
		if (loOperator == null) {
			System.out.println("LoOperator with id " + id + " not found");
			return new ResponseEntity<LoOperator>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LoOperator>(loOperator, HttpStatus.OK);
	}

	
	
	//-------------------Create a LoOperator--------------------------------------------------------
	
	@RequestMapping(value = "/loOperator/", method = RequestMethod.POST)
	public ResponseEntity<Void> createLoOperator(@RequestBody LoOperator loOperator, 	UriComponentsBuilder ucBuilder) {
		

		if (loOperatorService.isLoOperatorExist(loOperator)) {
			System.out.println("A LoOperator with name " + loOperator.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		//System.out.println("Creating LoOperator " + loOperator.toString());
		loOperatorService.saveLoOperator(loOperator);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/loOperator/{id}").buildAndExpand(loOperator.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
	//------------------- Update a LoOperator --------------------------------------------------------
	
	@RequestMapping(value = "/loOperator/{id}", method = RequestMethod.PUT)
	public ResponseEntity updateLoOperator(@PathVariable("id") long id, @RequestBody LoOperator loOperator) {
		System.out.println("Updating LoOperator " + id);
		loOperator.setId(BigDecimal.valueOf(id));
		
		if(loOperatorService.updateLoOperator(loOperator))
			return new ResponseEntity<LoOperator>(loOperator, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Operator with id " + id + " not found", HttpStatus.NOT_FOUND);
	}

	//------------------- Delete a LoOperator --------------------------------------------------------
	
	@RequestMapping(value = "/loOperator/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteLoOperator(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting LoOperator with id " + id);

		/*LoOperator loOperator = loOperatorService.findById(BigDecimal.valueOf(id));
		if (loOperator == null) {
			System.out.println("Unable to delete. LoOperator with id " + id + " not found");
			return new ResponseEntity<LoOperator>(HttpStatus.NOT_FOUND);
		}*/

		if(loOperatorService.deleteLoOperator(BigDecimal.valueOf(id)))
			return new ResponseEntity<String>("Deleted Operator ["+id+"]",HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("Unable to delete Operator with id [" + id + "] not found",HttpStatus.NO_CONTENT);
	}

	
	//------------------- Delete All LoOperator --------------------------------------------------------
	
	/*@RequestMapping(value = "/loOperator/", method = RequestMethod.DELETE)
	public ResponseEntity<LoOperator> deleteAllLoOperators() {
		System.out.println("Deleting All LoOperators");

		loOperatorService.deleteAllLoOperators();
		return new ResponseEntity<LoOperator>(HttpStatus.NO_CONTENT);
	}
*/
}
