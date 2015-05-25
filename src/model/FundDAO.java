package model;

import java.util.Arrays;
import java.util.Comparator;

import databean.FundBean;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class FundDAO extends GenericDAO<FundBean> {
	
	private CompareName compareNameASC = new CompareName(true);
	
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundBean.class, tableName, cp);
	}
	
	// UserDAO read method to fetch fund data based on fundId
	public FundBean[] matchLastName(int fundId) throws RollbackException {
		FundBean[] fund = match(MatchArg.equals("fundId", fundId));
		return fund;
	}
	
	// Return all funds available
	public FundBean[] read() throws RollbackException {
		FundBean[] funds= match();
		return funds;
	}
	
	// return fund name based on FundID
	public String getFundName(int fundId) throws RollbackException {
		FundBean[] fund= match(MatchArg.equals("fundId", fundId));
		return fund[0].getName();
	}
	
	//return fundId based on fund Name
	public int getFundId(String fundName) throws RollbackException {
		FundBean[] fund= match(MatchArg.equals("name", fundName));
		return fund[0].getFundId();
	}
	
	//return ticker based on fundID
	public String getTicker(int fundId) throws RollbackException {
		FundBean[] fund= match(MatchArg.equals("fundId", fundId));
		return fund[0].getSymbol();
	}
	
	//return ticker based on fundID
	public String getTickerbyName(String name) throws RollbackException {
		FundBean[] fund= match(MatchArg.equals("name", name));
		return fund[0].getSymbol();
	}
	
	public FundBean[] matchStartsWith(String fundName) throws RollbackException {
		FundBean[] fund= match(MatchArg.equals("name", fundName));
		return fund;
	}
	
	//Added by chang
	public FundBean getFundByName(String name) {
		FundBean[] fund = null;
		try {
			fund = match(MatchArg.equals("name", name));
		} catch (RollbackException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			e.printStackTrace();
		}
		if (fund == null || fund.length == 0) {
			return null;
		} else {
			return fund[0];
		}
	}
	
	// Added By Chang
	public FundBean getFundBySymbol(String name) {
		FundBean[] fund = null;
		try {
			fund = match(MatchArg.equals("symbol", name));
		} catch (RollbackException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			e.printStackTrace();
		}
		if (fund == null || fund.length == 0) {
			return null;
		} else {
			return fund[0];
		}
	}
	
	//Added by nash
			public FundBean[] lookupStartsWithName(String name) throws RollbackException {
				FundBean[] fundList = match(MatchArg.containsIgnoreCase("name",name));
				Arrays.sort(fundList,compareNameASC);
				return fundList;
			}
			
			public FundBean[] lookupStartsWithSymbol(String symbol) throws RollbackException {
				FundBean[] fundList = match(MatchArg.containsIgnoreCase("symbol",symbol));
				Arrays.sort(fundList,compareNameASC);
				return fundList;
			}
			
			private static class CompareName implements Comparator<FundBean> {
				private boolean ascending;
				
				public CompareName(boolean ascending) {
					this.ascending = ascending;
				}
				
				// Sorts by price then firstname or spouse's last then spouse's first,
				// depending on setting of spouseCompare variable.
				public int compare(FundBean e1, FundBean e2) {
					int lastCompare;
					
					if (ascending) {
						lastCompare = comparePrice(e1.getName(),e2.getName());
					} else {
						lastCompare = comparePrice(e2.getName(),e1.getName());
					}			
					 
					return lastCompare;			
				}
				
				private int comparePrice(String n1, String n2) {
					// The application never stores null names in the database, but
		/*			// just in case someone puts a null name in there...
					if (n1 == null && n2 == null) return 0;
					if (n1 == null) return -1;
					if (n2 == null) return 1;*/
					return n1.compareTo(n2);
				}
			}
 	// FundDAO create method to insert new fund to fund table
	/*public void create(FundBean fund) throws RollbackException {
		try {
			Transaction.begin();

			// Create a new employee in the database
			create(fund);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}*/
	
}