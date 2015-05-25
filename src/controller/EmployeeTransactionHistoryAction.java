package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.TransactionAllInfo;
import databean.TransactionBean;

public class EmployeeTransactionHistoryAction extends Action{

	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fphDAO;
	private CustomerDAO customerDAO;
	
	public EmployeeTransactionHistoryAction(Model model) {
		transactionDAO= model.getTransactionDAO();
		fphDAO= model.getFundPriceHistoryDAO();
		this.customerDAO = model.getCustomerDAO();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "empTransaction.do";
	}

	@Override
	public String perform(HttpServletRequest req) {
		// TODO Auto-generated method stub
		List<String> errors= new ArrayList<String>();
		req.setAttribute("errors", errors);
		
		try {
			if(req.getSession().getAttribute("employee")==null) {
				errors.add("Please log-in to access this page.");
				return "login.jsp";
			}
			
			if(req.getParameter("id")==null) {
				errors.add("Please select a customer.");
				return "ViewCustomer.do";
			}
			
			int cusId=0;
			try{
			cusId= Integer.parseInt(req.getParameter("id"));
			} catch(NumberFormatException e) {
				errors.add("Please select a proper customer.");
				return "ViewCustomer.do";
			}
			
			CustomerBean[] customer;
	        customer = customerDAO.match(MatchArg.equals("customerId", cusId));
	        if (customer == null || customer.length<=0) {
	        	System.out.println("here");
	            errors.add("User Id not found");
	            return "ViewCustomer.do";
	        }
			TransactionBean[] transactions= transactionDAO.getTransactions(cusId);
			
			List<TransactionAllInfo> buySellList= new ArrayList<TransactionAllInfo>();
			List<TransactionAllInfo> depositList= new ArrayList<TransactionAllInfo>();
			for(int i=0; i<transactions.length;i++) {
				if(transactions[i].getTransactionType().equals("Buy") || transactions[i].getTransactionType().equals("Sell")) {
					TransactionAllInfo buyTran= new TransactionAllInfo();
					buyTran.setCustomerId(transactions[i].getCustomerId());
					buyTran.setFundId(transactions[i].getFundId());
					double amount=((double)transactions[i].getAmount())/100;
					DecimalFormat df = new DecimalFormat("#0.00"); 
					String sAmount= df.format(amount);
					buyTran.setAmount(sAmount);
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					if(transactions[i].getExecuteDate() != null) {
						String date=dateFormat.format(transactions[i].getExecuteDate());
						buyTran.setExecuteDate(date);
					}
					buyTran.setFundname(transactions[i].getFundname());
					double numShares=(double)transactions[i].getNumberOfShares()/1000;
					DecimalFormat dfr = new DecimalFormat("#0.000"); 
					String sNumShares= dfr.format(numShares);
					buyTran.setNumberOfShares(sNumShares);
					buyTran.setStatus(transactions[i].getStatus());
					buyTran.setSymbol(transactions[i].getSymbol());
					buyTran.setTransactionType(transactions[i].getTransactionType());
					double buyPrice=0.0;
					if(transactions[i].getExecuteDate() != null) {
						buyPrice= fphDAO.matchDateFund(transactions[i].getExecuteDate(), transactions[i].getFundId());
					}
					DecimalFormat dfm = new DecimalFormat("#0.00"); 
					String sBuyPrice= dfm.format(buyPrice);
					buyTran.setBuyingPrice(sBuyPrice);
					buySellList.add(buyTran);
				} else {
					TransactionAllInfo depositTran= new TransactionAllInfo();
					depositTran.setCustomerId(transactions[i].getCustomerId());
					double amount=((double)transactions[i].getAmount())/100;
					DecimalFormat df = new DecimalFormat("#0.00"); 
					String sAmount= df.format(amount);
					depositTran.setAmount(sAmount);
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					if(transactions[i].getExecuteDate() != null) {
						String date=dateFormat.format(transactions[i].getExecuteDate());
						depositTran.setExecuteDate(date);
					}
					depositTran.setStatus(transactions[i].getStatus());
					depositTran.setTransactionType(transactions[i].getTransactionType());
					depositList.add(depositTran);
				}
			}
			req.setAttribute("buySellList", buySellList);
			req.setAttribute("depositList", depositList);
			req.setAttribute("cusId", cusId);

			
			return "empTransactionHistory.jsp";
		} catch(NumberFormatException e) {
			errors.add("User Id not a number");
			return "changePassword.jsp";
		} catch(RollbackException e) {
			errors.add(e.getMessage());
        	return "errors.jsp";
		}
	}

}
