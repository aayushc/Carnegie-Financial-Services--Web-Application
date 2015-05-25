package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.EmployeeBean;
import formbean.ChangePasswordForm;
import formbean.Emp_ChangePasswordForm;

public class Cus_ChangeOwnpassword extends Action{

private FormBeanFactory<ChangePasswordForm> formBeanFactory = FormBeanFactory.getInstance(ChangePasswordForm.class);
	
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public Cus_ChangeOwnpassword(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "cus_changeOwnPassword.do"; }
	
	public String perform(HttpServletRequest req) {
        
        List<String> errors= new ArrayList<String>();
		req.setAttribute("errors", errors);
        
        try {
        	ChangePasswordForm form = formBeanFactory.create(req);
	        //req.setAttribute("form",form);
	        
	        CustomerBean customer= (CustomerBean)req.getSession(false).getAttribute("customer");
	        if(req.getSession().getAttribute("customer")==null) {
				errors.add("Please log-in to access this page.");
				return "login.jsp";
			}
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	return "cus_ChangeOwnPassword.jsp";
	        }

	        if(req.getSession().getAttribute("customer")==null) {
				errors.add("Please log-in to access this page.");
				return "login.jsp";
			}
			if (customer == null) {
				return "login.jsp";
			}

			customer = customerDAO.read(customer.getCustomerId());
			if (customer == null) {
				return "login.jsp";
			}
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "cus_ChangeOwnPassword.jsp";
	        }
	        
	        if (customer.getPassword().matches((sanitize(form.getNewPassword())))) {
	        	errors.add("Please dont use old password, provide a new one");
   	        	return "cus_ChangeOwnPassword.jsp";
	        }
	        
	        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
	        	errors.add("Confirm Password does not match with the new one");
   	        	return "cus_ChangeOwnPassword.jsp";
	        }
	        
       		if (form.getAction().equals("changePass")) {
       			customerDAO.changePass(customer.getCustomerId(), form.getNewPassword());
       			String message= "Congratulations! Password changed succesfully.";
    			req.setAttribute("message", message);
    			return "cus_success.jsp";
       		}
       		
	        // If redirectTo is null, redirect to the index page
       		String message= "Congratulations! Password changed succesfully.";
			req.setAttribute("message", message);
			return "cus_success.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "cus_ChangeOwnPassword.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "cus_ChangeOwnPassword.jsp";
        }
    }
}
