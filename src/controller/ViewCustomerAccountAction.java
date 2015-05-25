package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.CustomerFundAllInfo;
import databean.PositionBean;
import formbean.CustomerLoginForm;

public class ViewCustomerAccountAction extends Action {
	private FormBeanFactory<CustomerLoginForm> formBeanFactory = FormBeanFactory
			.getInstance(CustomerLoginForm.class);
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private TransactionDAO tranDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fpDAO;

	public ViewCustomerAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		tranDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fpDAO = model.getFundPriceHistoryDAO();// TODO Auto-generated
												// constructor
												// stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "ViewCustomerAccount.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {
			if(request.getSession().getAttribute("employee")==null) {
				errors.add("Please log-in to register new employee.");
				return "login.jsp";
			}
			int customerId=0;
			try{
			customerId =Integer.parseInt(request.getParameter("id"));
			} catch(NumberFormatException e) {
				errors.add("Please enter a valid customer id.");
				return "ViewCustomer.do";				
			}
			//System.out.println(customerId);
			CustomerBean customerInfo = customerDAO.read(customerId);
			if(customerInfo == null) {
				errors.add("Please enter a valid customer id.");
				return "ViewCustomer.do";
			}
			request.setAttribute("customerInfo", customerInfo);
			if(tranDAO.getTradeDay()!=null) {
			Date lastDay = tranDAO.getTradeDay().getExecuteDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String date=dateFormat.format(lastDay);
			request.setAttribute("lastDay", date);
			}
			//System.out.println(customerInfo.getCustomerId());

			
			PositionBean[] positions = positionDAO.getCustomerFunds(customerInfo
					.getCustomerId());

			List<CustomerFundAllInfo> custFunds = new ArrayList<CustomerFundAllInfo>();

			for (int i = 0; i < positions.length; i++) {
				if(positions[i].getNumberOfShares()!=0) {
				CustomerFundAllInfo each = new CustomerFundAllInfo();
				each.setFundId(positions[i].getFundId());
				Double shares= (double)positions[i].getNumberOfShares()/1000;
				DecimalFormat df = new DecimalFormat("#0.000"); 
				String sShares= df.format(shares);
				each.setShares(sShares);
				each.setName(fundDAO.getFundName(positions[i].getFundId()));
				Double price= fpDAO.getFundPrice(positions[i].getFundId());
				DecimalFormat dfr = new DecimalFormat("#0.00"); 
				String sPrice= dfr.format(price);
				each.setPrice(sPrice);
				Double position= ((double)positions[i].getNumberOfShares()/1000)*fpDAO.getFundPrice(positions[i].getFundId());
				DecimalFormat dfm = new DecimalFormat("#0.00"); 
				String sposition= dfm.format(position);
				each.setPosition(sposition);
				custFunds.add(each);
				}
			}
			request.setAttribute("allCustFunds", custFunds);
			return "viewcustomeraccount.jsp";// TODO Auto-generated method stub
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "errors.jsp";
		}

	}
}
