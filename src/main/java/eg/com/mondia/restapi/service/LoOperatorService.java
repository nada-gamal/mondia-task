package eg.com.mondia.restapi.service;

import java.math.BigDecimal;
import java.util.List;

import eg.com.mondia.restapi.model.LoOperator;



public interface LoOperatorService {
	
	public LoOperator findById(BigDecimal id);
	
	public LoOperator findByName(String name);
	
	public void saveLoOperator(LoOperator loOperator);
	
	public boolean updateLoOperator(LoOperator loOperator);
	
	public boolean deleteLoOperator(BigDecimal id);

	public List<LoOperator> findAllLoOperators(); 
	
	//void deleteAllLoOperators();
	
	public boolean isLoOperatorExist(LoOperator LoOperator);
	
}
