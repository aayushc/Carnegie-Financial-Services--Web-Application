package model;

import databean.FundPriceHistoryBean;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> {
	private ComparePrice comparePriceASC = new ComparePrice(true);
	private ComparePrice comparePriceDSC = new ComparePrice(false);
	
	public FundPriceHistoryDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundPriceHistoryBean.class, tableName, cp);
	}
	
	// HistoryDAO read method to fetch histiry data based on fund id
	public FundPriceHistoryBean[] matchFundId(String fundId) throws RollbackException {
		FundPriceHistoryBean[] history = match(MatchArg.equals("fundId", fundId));
		return history;
	}
	
	// FundPricehistoryDAO read method to fetch funds based on price date
	public FundPriceHistoryBean[] matchPriceDate(Date priceDate) throws RollbackException {
		FundPriceHistoryBean[] history = match(MatchArg.equals("priceDate", priceDate));
		return history;
	}
	
	//get price of a fund on a particular date
	public double matchDateFund(Date priceDate, int fundId) throws RollbackException {
		FundPriceHistoryBean[] history = match(MatchArg.and(MatchArg.equals("fundId", fundId), MatchArg.equals("priceDate", priceDate)));
		return (double)history[0].getPrice()/100;
	}
	//get Latest price of a particular fund based on fund id
	public double getFundPrice(int fundId) throws RollbackException {
		FundPriceHistoryBean[] funds= match(MatchArg.equals("fundId", fundId));
		/*System.out.println("new");
		Arrays.sort(funds,compareASC);
		for(int i=0;i<funds.length;i++) {
			System.out.println(funds[i].getPrice());
		}*/
		if (funds==null || funds.length==0) {
			return 0.0;
		}
		double price= (double)funds[funds.length-1].getPrice()/100;
		return price;
	}
	
	public FundPriceHistoryBean getLatestFund(int fundId) throws RollbackException {
		FundPriceHistoryBean[] funds= match(MatchArg.equals("fundId", fundId));		
		return funds[funds.length-1];
	}
	
	public FundPriceHistoryBean getSecondLatestFund(int fundId) throws RollbackException {
		FundPriceHistoryBean[] funds= match(MatchArg.equals("fundId", fundId));
		return funds[funds.length-2];
	}
	///Naz changes
	public FundPriceHistoryBean[] getFundAsc(int fundId) throws RollbackException {
		FundPriceHistoryBean[] funds= match(MatchArg.equals("fundId", fundId));
		Arrays.sort(funds,comparePriceASC);
		for(int i=0;i<funds.length;i++) {
			System.out.println(funds[i]);
		}
		return funds;
	}
	
	public FundPriceHistoryBean[] getFundDsc(int fundId) throws RollbackException {
		FundPriceHistoryBean[] funds= match(MatchArg.equals("fundId", fundId));
		Arrays.sort(funds,comparePriceDSC);
		for(int i=0;i<funds.length;i++) {
			System.out.println(funds[i]);
		}
		return funds;
	}	
	
	public double getFundPrice2(int fundId) throws RollbackException {
		FundPriceHistoryBean[] funds= match(MatchArg.equals("fundId", fundId));
		long price;
		if (funds.length>1) {
			price = funds[funds.length-2].getPrice();
		} else {
			price = 0;
		}
		return price/100;
	}
	
	// HistoryDAO read method to fetch histiry data based on fund id
	public FundPriceHistoryBean[] matchFundId(int fundId) throws RollbackException {
		FundPriceHistoryBean[] history = match(MatchArg.equals("fundId", fundId));
		return history;
	}
	
	private static class ComparePrice implements Comparator<FundPriceHistoryBean> {
		private boolean ascending;
		
		public ComparePrice(boolean ascending) {
			this.ascending = ascending;
		}
		
		// Sorts by price then firstname or spouse's last then spouse's first,
		// depending on setting of spouseCompare variable.
		public int compare(FundPriceHistoryBean e1, FundPriceHistoryBean e2) {
			int lastCompare;
			
			if (ascending) {
				lastCompare = comparePrice(e1.getPrice(),e2.getPrice());
			} else {
				lastCompare = comparePrice(e2.getPrice(),e1.getPrice());
			}			
			 
			return lastCompare;			
		}
		
		private int comparePrice(Long n1, Long n2) {
			// The application never stores null names in the database, but
/*			// just in case someone puts a null name in there...
			if (n1 == null && n2 == null) return 0;
			if (n1 == null) return -1;
			if (n2 == null) return 1;*/
			return n1.compareTo(n2);
		}
	};
	//till here Naz
	//Thomas
	public FundPriceHistoryBean getPrice(int id) {
		FundPriceHistoryBean[] tmp = null;
		try {
			tmp = match(MatchArg.and(MatchArg.max("priceDate"), MatchArg.equals("fundId", id)));
		} catch (RollbackException e) {
			e.printStackTrace();
			if (Transaction.isActive())
				Transaction.rollback();
		}
		if (tmp == null || tmp.length == 0)
			return null;
		return tmp[0];
	}
	
	public void updateAll(ArrayList<FundPriceHistoryBean> prices, Date date) throws RollbackException {
		for (FundPriceHistoryBean f : prices) {
			f.setPriceDate(date);
			createAutoIncrement(f);
		}
	}
	
	// get all
	public FundPriceHistoryBean[] getAll() throws RollbackException {
		FundPriceHistoryBean[] history = match();
		return history;
	}
}
