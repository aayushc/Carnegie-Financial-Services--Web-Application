package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.CustomerFundAllInfo;
import databean.PositionBean;
import databean.TransactionBean;
import formbean.BuyFundsForm;
import formbean.SellFundsForm;
import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class SellFundAction extends Action{

	private FormBeanFactory<SellFundsForm> formBeanFactory= FormBeanFactory.getInstance(SellFundsForm.class);
	
	private CustomerDAO cusDAO;
	private FundDAO fundDAO;
	private PositionDAO posDAO;
	private FundPriceHistoryDAO fpDAO;
	private TransactionDAO tranDAO;
	
	public SellFundAction(Model model) {
		cusDAO= model.getCustomerDAO();
		fundDAO= model.getFundDAO();
		posDAO= model.getPositionDAO();
		fpDAO= model.getFundPriceHistoryDAO();
		tranDAO= model.getTransactionDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "sellFund.do";
	}

	@Override
	public String perform(HttpServletRequest req) {
		// TODO Auto-generated method stub
		List<String> errors= new ArrayList<String>();
		req.setAttribute("errors", errors);
		if(req.getSession().getAttribute("customer")==null) {
			errors.add("Please log-in to access this page.");
			return "login.jsp";
		}
		
		try {
			//check if user is logged in
			if(req.getSession().getAttribute("customer")==null) {
				errors.add("Please log-in to buy the funds.");
				return "login.jsp";
			}
			
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
			
			SellFundsForm sellForm= formBeanFactory.create(req);
			
			//return the JSP page if form is not present.This means user has till now not filled the form.
			if(!sellForm.isPresent()) {
				return "sellFunds.jsp";
			}
			
			errors.addAll(sellForm.getValidationErrors());
			if(errors.size()>0) {
				return "sellFunds.jsp";
			}
			Transaction.begin();
			PositionBean[] positions1= posDAO.getCustomerFunds(cust.getCustomerId());
			String fundName= sellForm.getFundName();
			double numberOfShares= Double.parseDouble(sellForm.getNumberOfShares());
			double originalNoShares= 0.0;
			//calculate existing shares of the fund with the customer
			for(int i=0; i<positions1.length;i++) {
				String eachFundName= fundDAO.getFundName(positions1[i].getFundId());
				if(eachFundName.equals(fundName)) {
					originalNoShares= originalNoShares+positions1[i].getNumberOfShares();
				}
			}
			originalNoShares=originalNoShares/1000;

			if(originalNoShares < numberOfShares) {
				errors.add("The number of shares exceeds your existing number of shares.");
				return "sellFunds.jsp";
			}
			
			/*TransactionBean[] tran= tranDAO.getPendingTransactions(cust.getCustomerId(), fundDAO.getFundId(fundName));
			double pendingShares=0.0;
			if(tran!=null && tran.length>0) {
				for(int i=0; i<tran.length;i++) {
					pendingShares= pendingShares + (double)tran[i].getNumberOfShares()/1000;
				}
			}
			
			if((originalNoShares-pendingShares) <numberOfShares) {
				errors.add("You already have placed Sell request for "+pendingShares+". Cannot process this transaction.");
				return "sellFunds.jsp";
			}*/
			
			//commit transaction in transaction table
			TransactionBean tr= new TransactionBean();
			tr.setAmount(0);
			tr.setCustomerId(cust.getCustomerId());
			tr.setExecuteDate(null);
			tr.setFundId(fundDAO.getFundId(fundName));
			tr.setNumberOfShares((long)(numberOfShares*1000));
			tr.setTransactionType("Sell");
			tr.setFundname(fundName);
			tr.setSymbol(fundDAO.getTickerbyName(fundName));
			tr.setStatus("PENDING");
			tranDAO.create(tr);
			
			// add new row in position table
			PositionBean pos = posDAO.getShares(cust.getCustomerId(), fundDAO.getFundId(fundName));
			pos.setNumberOfShares(pos.getNumberOfShares() - (long)(numberOfShares*1000));
			posDAO.update(pos);
			Transaction.commit();
			req.setAttribute("message",	 "Your request is being processing");
			return "transaction.do";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
        	return "errors.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
        	return "errors.jsp";
		}
	}

}
