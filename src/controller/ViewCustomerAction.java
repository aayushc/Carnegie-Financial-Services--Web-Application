package controller;

import model.CustomerDAO;
import databean.CustomerBean;
import model.Model;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;

public class ViewCustomerAction extends Action {
	private CustomerDAO customerDAO;

	public ViewCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();// TODO Auto-generated constructor
												// stub
	}

	@Override
	public String getName() {
		return "ViewCustomer.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
	        try{
	        	if(request.getSession().getAttribute("employee")==null) {
	    			errors.add("Please log-in to register new employee.");
	    			return "login.jsp";
	    		}
	        	CustomerBean[] customers=customerDAO.match();
	        	//System.out.println(customers[0].getAddrLine1());
	        	request.setAttribute("customerList", customers);
	        	return "viewcustomer.jsp";
	        }catch(RollbackException e){
	        	errors.add(e.getMessage());
	    		return "error.jsp";
	        }
	
	}

}
