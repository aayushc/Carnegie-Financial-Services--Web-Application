package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.EmployeeBean;
import databean.TransactionBean;
import formbean.ChangePasswordForm;
import formbean.Emp_DepositCheckForm;

public class Emp_DepositCheckAction extends Action {
	private CustomerDAO customerDAO;
	private TransactionDAO transDAO;
	private FormBeanFactory<Emp_DepositCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(Emp_DepositCheckForm.class);

	public Emp_DepositCheckAction(Model model) {
		this.customerDAO = model.getCustomerDAO();
		this.transDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		return "emp_depositCheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		
		int userId=0;
		try{
		userId = Integer.parseInt(request.getParameter("id"));
		} catch(NumberFormatException e) {
			errors.add("User Id not a number");
			return "ViewCustomer.do";
		}
        try {
	    	Emp_DepositCheckForm form = formBeanFactory.create(request);
	    	//request.setAttribute("form",form);
	        
	    	if(request.getSession().getAttribute("employee")==null) {
				errors.add("Please log-in to deposit the check.");
				return "login.jsp";
			}
	    	
	        CustomerBean[] customer;
	        customer = customerDAO.match(MatchArg.equals("customerId", userId));
	        if (customer == null || customer.length <=0) {
	            errors.add("User Id not found");
	            return "ViewCustomer.do";
	        }
	        if(customer!= null && customer.length>=1) {
	        	request.setAttribute("username", customer[0]);
	        }
	        
	        if (!form.isPresent()) {
	        	request.setAttribute("userId",userId);
	        	return "depositCheck.jsp";
	        }

	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "depositCheck.jsp";
	        }     
	        
	        			
			Transaction.begin();
			String deposit = form.getDeposit();
			
			
			double amount = Double.parseDouble(deposit);
			TransactionBean trans = new TransactionBean();
			trans.setAmount((long)(amount*100));
			trans.setCustomerId(customer[0].getCustomerId());
			trans.setExecuteDate(null);
			trans.setTransactionType("DEPOSIT");
			trans.setStatus("PENDING");
			transDAO.createAutoIncrement(trans);
			if (Transaction.isActive())
            	Transaction.commit();
			
			DecimalFormat dfAmount = new DecimalFormat("###,###,###,###,###,###,##0.00");
			dfAmount.setMaximumFractionDigits(2);
			dfAmount.setMinimumFractionDigits(2);
			request.setAttribute("message", "Your deposit request is being processing");
			return "success.jsp";

		} catch (FormBeanException e) {
			if (Transaction.isActive())
				Transaction.rollback();
			errors.add(e.getMessage());
			return "depositCheck.jsp";
		} catch (RollbackException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			errors.add(e.getMessage());
			return "depositCheck.jsp";
		}

	}

}
