package eg.com.mondia.restapi.dao;

import java.math.BigDecimal;
import java.util.List;

import eg.com.mondia.restapi.model.LoOperator;

public interface LoOperatorDao {

	 	public void save(LoOperator op);
	 	
	 	public boolean updateOperator(LoOperator op);
	 	
	 	public LoOperator findById(BigDecimal id);
	 	
	 	public LoOperator findByName(String name);
	 	
	 	public boolean delete(BigDecimal id);
	 	
	 	public List<LoOperator> findAll();
	 	
	 	//public void deleteAll();
	 	
}
