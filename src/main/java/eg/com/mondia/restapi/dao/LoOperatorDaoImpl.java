package eg.com.mondia.restapi.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import eg.com.mondia.restapi.model.LoOperator;

@Repository("loOperatorDao")
public class LoOperatorDaoImpl extends AbstractDao<BigDecimal, LoOperator> implements LoOperatorDao{
	
	 	public void save(LoOperator op) {
	 		persist(op);
	 	}

	 	public boolean updateOperator(LoOperator op) {
	 		LoOperator currentOperator = (LoOperator) getByKey(op.getId());
			
			if (currentOperator==null) {
				return false;
			}

			currentOperator.setName(op.getName());
			currentOperator.setCountry(op.getCountry());
			currentOperator.setPackageId(op.getPackageId());
			currentOperator.setServiceId(op.getServiceId());

	 		
	 		update(currentOperator);
	 		return true;
	 	}

	 	public LoOperator findById(BigDecimal id) {
	 		LoOperator op = (LoOperator) getByKey(id);
	 		return op; 
	 	}

	 	public boolean delete(BigDecimal id) {
	 		LoOperator op = (LoOperator) getByKey(id);
	 		if (op != null){
	 			delete(op);
	 			return true;
	 		}
	 		else
	 			return false;
	 	}

	 	public List<LoOperator> findAll() {
	 		@SuppressWarnings("unchecked")
			List<LoOperator> loOperators = (List<LoOperator>) getEntityManager().createQuery("from LoOperator").getResultList();
	 		return loOperators;
	 	}

		@Override
		public LoOperator findByName(String name) {
			// TODO Auto-generated method stub
			try{
	            LoOperator loOperator = (LoOperator) getEntityManager()
	                    .createQuery("SELECT op FROM LoOperator op WHERE op.name LIKE :loOperatorName")
	                    .setParameter("loOperatorName", name)
	                    .getSingleResult();
	             
	            if(loOperator!=null){
	            	return loOperator;
	            }
	             
	        }catch(NoResultException ex){
	            return null;
	        }
			return null;
		}
}