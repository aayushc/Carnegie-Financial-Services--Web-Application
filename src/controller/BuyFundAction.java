package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBean;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.CustomerFundAllInfo;
import databean.FundAllInfo;
import databean.FundBean;
import databean.PositionBean;
import databean.TransactionBean;
import formbean.BuyFundsForm;
import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class BuyFundAction extends Action{

	private FormBeanFactory<BuyFundsForm> formBeanFactory= FormBeanFactory.getInstance(BuyFundsForm.class);
	
	private CustomerDAO cusDAO;
	private FundDAO fundDAO;
	private PositionDAO posDAO;
	private FundPriceHistoryDAO fpDAO;
	private TransactionDAO tranDAO;
	
	//constructor
	public BuyFundAction(Model model) {
		cusDAO= model.getCustomerDAO();
		fundDAO= model.getFundDAO();
		posDAO= model.getPositionDAO();
		fpDAO= model.getFundPriceHistoryDAO();
		tranDAO= model.getTransactionDAO();
	}
		
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "buyFund.do";
	}

	@Override
	public String perform(HttpServletRequest req) {
		// TODO Auto-generated method stub
		List<String> errors= new ArrayList<String>();
		req.setAttribute("errors", errors);
		
		try {
			//check if user is logged in
			if(req.getSession().getAttribute("customer")==null) {
				errors.add("Please log-in to buy the funds.");
				return "login.jsp";
			}
			
			// below code to display all the funds present to the user
			FundBean[] totalFunds= fundDAO.read();
			
			if(totalFunds.length==0) {
				errors.add("No funds exist.");
				//change here to home page
				return "buyFunds.jsp";
			}
			
			FundBean[] funds = null;
			
			int searchFundId;
			if (req.getParameter("searchId")!= null){
				searchFundId = Integer.parseInt(req.getParameter("searchId"));
				funds= fundDAO.matchLastName(searchFundId);
			} else {
				searchFundId = totalFunds[0].getFundId();
				funds= fundDAO.read();
			}		
			
			List<FundAllInfo> allFunds= new ArrayList<FundAllInfo>();
			
			String[] allFundnames= new String[funds.length];
			for(int i=0; i<funds.length;i++) {
				FundAllInfo oneFund= new FundAllInfo();
				oneFund.setFundId(funds[i].getFundId());
				oneFund.setName(funds[i].getName());
				oneFund.setSymbol(funds[i].getSymbol());
				Double price= fpDAO.getFundPrice(funds[i].getFundId());
				DecimalFormat df = new DecimalFormat("#0.00"); 
				String sAmount= df.format(price);
				oneFund.setPrice(sAmount);
				allFunds.add(oneFund);
				
				allFundnames[i]= funds[i].getName();
			}
			req.setAttribute("allFunds", allFunds);
			//for serach button
			req.setAttribute("allFundnames", allFundnames);
			//till here code to retieve all the funds
			
			// below code to show existing funds of the users
			CustomerBean cust= (CustomerBean)req.getSession().getAttribute("customer");
			PositionBean[] positions= posDAO.getCustomerFunds(cust.getCustomerId());
			
			List<CustomerFundAllInfo> custFunds= new ArrayList<CustomerFundAllInfo>();
			
			for(int i=0; i<positions.length;i++) {
				if(positions[i].getNumberOfShares() !=0) {
				CustomerFundAllInfo each= new CustomerFundAllInfo();
				each.setCustomerId(positions[i].getCustomerId());
				each.setFundId(positions[i].getFundId());
				double numShares=(double)positions[i].getNumberOfShares()/1000;
				DecimalFormat df = new DecimalFormat("#0.000"); 
				String snumShares= df.format(numShares);
				each.setShares(snumShares);
				each.setName(fundDAO.getFundName(positions[i].getFundId()));
				each.setSymbol(fundDAO.getTicker(positions[i].getFundId()));
				DecimalFormat dfr = new DecimalFormat("#0.00"); 
				Double price=fpDAO.getFundPrice(positions[i].getFundId());
				String sPrice=dfr.format(price);
				each.setPrice(sPrice);
				custFunds.add(each);
				}
			}
			req.setAttribute("allCustFunds", custFunds);
			//till here to retrieve code for existing funds of a customer
			
						
			BuyFundsForm buyForm= formBeanFactory.create(req);
			req.setAttribute("cash", ((double) cust.getCash()) / 100);
			
			//return the JSP page if form is not present.This means user has till now not filled the form.
			if(!buyForm.isPresent()) {
				return "buyFunds.jsp";
			}
			errors.addAll(buyForm.getValidationErrors());
			if(errors.size()>0) {
				return "buyFunds.jsp";
			}
			
			if(allFunds.size() ==0) {
				errors.add("No matches for fund name starts with \""+buyForm.getFund()+"\" ");
				return "buyFunds.jsp";
			}
			req.setAttribute("cash", ((double) cust.getCash()) / 100);
			String fundName= buyForm.getFund();
			
						
			//amount before the transaction
			double amount= Double.parseDouble(buyForm.getAmount());
			
			// amount of the transaction
			Transaction.begin();
			double cash= cusDAO.getCashBalance(cust.getCustomerId());
			
			if(cash< amount) {
				errors.add("You do not have sufficient cash in your account.");
				return "buyFunds.jsp";
			}
			
			//commit transaction in transaction table
			TransactionBean tr= new TransactionBean();
			tr.setAmount((long)(amount*100));
			tr.setCustomerId(cust.getCustomerId());
			tr.setExecuteDate(null);
			tr.setFundId(fundDAO.getFundId(fundName));
			tr.setNumberOfShares(0);
			tr.setTransactionType("Buy");
			tr.setFundname(fundName);
			tr.setSymbol(fundDAO.getTickerbyName(fundName));
			tr.setStatus("PENDING");
			tranDAO.create(tr);
			
			// commit the reduced cash in customer table
			cusDAO.changeCashBalance2(cust.getCustomerId(), (cash-amount)*100);
			if (Transaction.isActive()) {
				Transaction.commit();
			}
			// add new row in position table
			/*PositionBean position= new PositionBean();
			position.setCustomerId(cust.getCustomerId());
			position.setFundId(fundDAO.getFundId(fundName));
			position.setNumberOfShares(0);
			posDAO.create(position);*/

			DecimalFormat nf = new DecimalFormat("###,###,###,###,##0.00");
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
		
			req.setAttribute("message",
					"Your request is being processing");
			return "buyFunds.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
        	return "errors.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
        	return "errors.jsp";
		}
	}
	
	

}
