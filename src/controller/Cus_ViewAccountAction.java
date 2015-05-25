package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.PositionBean;
import databean.FundBean;
import databean.FundPriceHistoryBean;
import databean.TransactionBean;
import databean.CustomerFundAllInfo;
import model.Model;
import model.PositionDAO;
import model.CustomerDAO;
import model.FundDAO;
import model.TransactionDAO;
import model.FundPriceHistoryDAO;

public class Cus_ViewAccountAction extends Action {
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fpDAO;
	private TransactionDAO tranDAO;

	public Cus_ViewAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
		fpDAO = model.getFundPriceHistoryDAO();
		tranDAO = model.getTransactionDAO();// TODO Auto-generated constructor
											// stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Cus_ViewAccount.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {

			CustomerBean customer = (CustomerBean) request.getSession()
					.getAttribute("customer");
			int customerId=customer.getCustomerId();
			CustomerBean customerInfo = customerDAO.read(customerId);
			request.setAttribute("accountInfo", customerInfo);

			if(request.getSession().getAttribute("customer")==null) {
				errors.add("Please log-in to view the account.");
				return "login.jsp";
			}
			
			Date lastDay = tranDAO.getTradeDay().getExecuteDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String date=dateFormat.format(lastDay);
			request.setAttribute("lastDay", date);

			
			//request.getSession(true).setAttribute("customer", customer);
			//CustomerBean cust= (CustomerBean)request.getSession().getAttribute("customer");
			PositionBean[] positions= positionDAO.getCustomerFunds(customer.getCustomerId());
			
			List<CustomerFundAllInfo> custFunds= new ArrayList<CustomerFundAllInfo>();
			
			for(int i=0; i<positions.length;i++) {
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

		} catch (Exception e) {
			
		}
		return "cus_viewaccount.jsp";
	}

}
