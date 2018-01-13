package eg.com.mondia.restapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eg.com.mondia.restapi.dao.LoOperatorDao;
import eg.com.mondia.restapi.model.LoOperator;;

@Service("loOperatorService")
@Transactional
public class LoOperatorServiceImpl implements LoOperatorService{
	
	@Autowired
	LoOperatorDao loOperatorDao;
	
//	private static final AtomicLong counter = new AtomicLong();
//	
//	private static List<LoOperator> loOperators;
	
	/*static{
		loOperators= populateDummyLoOperators();
	}*/

	public List<LoOperator> findAllLoOperators() {
		return loOperatorDao.findAll();
	}
	
	public LoOperator findById(BigDecimal id) {
		return loOperatorDao.findById(id);
	}
	
	public LoOperator findByName(String name) {
		return loOperatorDao.findByName(name);
	}
	
	public void saveLoOperator(LoOperator loOperator) {
		loOperatorDao.save(loOperator);
	}

	public boolean updateLoOperator(LoOperator loOperator) {
		return loOperatorDao.updateOperator(loOperator);
	}

	public boolean deleteLoOperator(BigDecimal id) {
		return loOperatorDao.delete(id);
	}

	public boolean isLoOperatorExist(LoOperator loOperator) {
		return findByName(loOperator.getName())!=null;
	}

}
