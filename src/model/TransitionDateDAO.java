package model;

import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.DateBean;

public class TransitionDateDAO extends GenericDAO<DateBean>{
	public TransitionDateDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(DateBean.class, tableName, pool);
	}
	
	public Date getLastTransitionDay() {
		DateBean[] dates = null;
		try {
			dates = match(MatchArg.max("date"));
		} catch (RollbackException e) {
			e.printStackTrace();
			if (org.genericdao.Transaction.isActive())
				org.genericdao.Transaction.rollback();
		}
		if (dates == null || dates.length == 0)
			return null;
		return dates[0].getDate();
	}
}
