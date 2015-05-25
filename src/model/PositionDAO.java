package model;

import databean.PositionBean;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class PositionDAO extends GenericDAO<PositionBean> {
	
	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PositionBean.class, tableName, cp);
	}
	
	// PositionDAO read method to fetch customer's fund data based on customerId
	public PositionBean[] getCustomerFunds(int customerId) throws RollbackException {
		PositionBean[] position = match(MatchArg.equals("customerId", customerId));
		return position;
	}
	
	// PositionDAO read method to fetch customer data based on fundId
	public PositionBean[] matchLastName(int fundId) throws RollbackException {
		PositionBean[] position = match(MatchArg.equals("fundId", fundId));
		return position;
	}
	
	// PositionDAO create method to insert new employee to employee table
	/*public void create(PositionBean position) throws RollbackException {
		try {
			Transaction.begin();

			// Create a new employee in the database
			create(position);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}*/
	

	//get shares
	public PositionBean getShares(int customerId, int fundId) {
		PositionBean[] tmp = null;
		try {
			tmp = match(MatchArg.and(MatchArg.equals("customerId", customerId), MatchArg.equals("fundId", fundId)));
		} catch (RollbackException e) {
			e.printStackTrace();
			if (Transaction.isActive())
				Transaction.rollback();
		}
		
		if (tmp == null || tmp.length == 0)
			return null;
		else
			return tmp[0];
 	}
}