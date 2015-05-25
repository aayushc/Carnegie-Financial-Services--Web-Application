package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import databean.CustomerBean;
import databean.DateBean;
import databean.FundPriceHistoryBean;
import databean.PositionBean;
import databean.TransactionBean;
import formbean.TransitionDayForm;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class TransactionDAO extends GenericDAO<TransactionBean> {
	
	CustomerDAO customerDAO;
	FundPriceHistoryDAO histDAO;
	PositionDAO posDAO;
	TransitionDateDAO dateDAO;
	
	public TransactionDAO(ConnectionPool pool, String tableName, CustomerDAO cdao, FundPriceHistoryDAO hdao, PositionDAO pdao, TransitionDateDAO tdao) throws DAOException {
		super(TransactionBean.class, tableName, pool);
		customerDAO = cdao;
		histDAO = hdao;
		posDAO = pdao;
		dateDAO = tdao;
	}
	
	// TransactionDAO read method to fetch data based on transactionId
	public TransactionBean[] matchLastName(int transactionId) throws RollbackException {
		TransactionBean[] transaction = match(MatchArg.equals("transactionId", transactionId));
		return transaction;
	}	
	
	//get all transactions related to a customer
	public TransactionBean[] getTransactions(int customerId) throws RollbackException {
		TransactionBean[] transaction = match(MatchArg.equals("customerId", customerId));
		return transaction;
	}
	
	public TransactionBean[] getPendingTransactions(int customerId, int fundId) throws RollbackException {
		TransactionBean[] transaction= match(MatchArg.and(MatchArg.equals("customerId", customerId), MatchArg.equals("fundId", fundId), MatchArg.equals("transactionType","Sell"), MatchArg.equals("status", "PENDING")));
		return transaction;
	}
	
	//Stephanie
	public TransactionBean getTradeDay()throws RollbackException{
		TransactionBean[] transaction = match(MatchArg.max("executeDate"));
		System.out.println(transaction.length);
		if(transaction.length==0)return null;
		return transaction[0];
	}
	
	//Nazh
		public TransactionBean[] getDailyTransactions(int fundId, Date executeDate) throws RollbackException {
			TransactionBean[] transaction= match(MatchArg.and(MatchArg.equals("fundId", fundId), MatchArg.equals("executeDate", executeDate), MatchArg.equals("transactionType","Buy"), MatchArg.equals("status", "APPROVED")));
			return transaction;
		}
	// TransactionDAO create method to insert new transaction
	/*public void create(TransactionBean transaction) throws RollbackException {
		try {
			Transaction.begin();

			// Create a new employee in the database
			create(transaction);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}*/
	//Thomas
	public void clearPending(Date date) throws RollbackException {			
		// Only handle pending BUYs and SELLs
		TransactionBean[] pending = match(MatchArg.equals("status", "PENDING"));
		System.out.println("Handling all pending transactions.");
		for (TransactionBean t : pending) {
			if (t.getTransactionType().equals("Sell")) {
				CustomerBean user = customerDAO.read(t.getCustomerId());
				if (user == null)
					continue;
				FundPriceHistoryBean price = histDAO.getPrice(t.getFundId());
				if (price == null)
					continue;
					
				// Update the cash amount.
				double sellAmount = (double)price.getPrice()/100 * ((double)t.getNumberOfShares()/1000);
				long sellam= (long)(sellAmount*100);
				t.setAmount(sellam);
				user.setCash(user.getCash() + sellam);
				customerDAO.update(user);
				/*PositionBean pos = posDAO.getShares(t.getCustomerId(), t.getFundId());
				pos.setNumberOfShares(pos.getNumberOfShares() - buySh);
				posDAO.update(pos);*/
				
			} else if (t.getTransactionType().equals("Buy")){
				FundPriceHistoryBean price = histDAO.getPrice(t.getFundId());
				if (price == null)
					continue;
				
				// Update the position table.
				PositionBean pos = posDAO.getShares(t.getCustomerId(), t.getFundId());
				double buyShares = (double)t.getAmount()/ price.getPrice();
				long buySh = (long)(buyShares*1000);
				if (pos == null) {
					pos = new PositionBean();
					pos.setCustomerId(t.getCustomerId());
					pos.setFundId(t.getFundId());
					pos.setNumberOfShares(buySh);
					posDAO.createAutoIncrement(pos);
					}
				else {
					pos.setNumberOfShares(pos.getNumberOfShares() + buySh);
					posDAO.update(pos);
				}
				t.setNumberOfShares(buySh);		
			} else if (t.getTransactionType().equals("DEPOSIT")) {
				CustomerBean user = customerDAO.read(t.getCustomerId());
				if (user == null)
					continue;
					
				// Update the cash amount.
				long amount = t.getAmount();
				user.setCash(user.getCash() + amount);
				customerDAO.update(user);
			}
			t.setExecuteDate(date);
			t.setStatus("APPROVED");
			update(t);
		}
	}
	
	public List<String> transitionDay(TransitionDayForm form) {
		ArrayList<String> errors = new ArrayList<String>();
		System.out.println("tday is called");

		try {
			boolean inTransaction = org.genericdao.Transaction.isActive();
        	org.genericdao.Transaction.begin();
        	
			Date lastDate = dateDAO.getLastTransitionDay();
			System.out.println("get last date success");

			if (lastDate != null && (lastDate.after(form.date) || lastDate.equals(form.date))) {
				errors.add("New transition date should be after last transition date.");
				org.genericdao.Transaction.commit();
			}
			
			// Update all prices.
			histDAO.updateAll(form.prices, form.date);
			System.out.println("price updated");
			// Handle all pending transactions. 
			clearPending(form.date);
			
			// Add an new transition date.
			DateBean newDate = new DateBean();
			newDate.setDate(form.date);
			dateDAO.createAutoIncrement(newDate);
			if (!inTransaction)
				org.genericdao.Transaction.commit();
			System.out.println("New transition day: " + form.date);
		} catch (RollbackException e) {
			if (org.genericdao.Transaction.isActive())
				org.genericdao.Transaction.rollback();
			errors.add("Error happened when processing transition day, the database rollbacked.");
		}
		return errors;
	}

}