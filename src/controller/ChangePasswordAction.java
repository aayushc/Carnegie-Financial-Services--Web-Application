package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import addon.SendMail;

import databean.*;
import model.*;
import formbean.*;

public class ChangePasswordAction extends Action {
	
	private FormBeanFactory<ChangePasswordForm> formBeanFactory = FormBeanFactory.getInstance(ChangePasswordForm.class);
	
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public ChangePasswordAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "changePassword.do"; }
	
	public String perform(HttpServletRequest req) {
        
        List<String> errors= new ArrayList<String>();
		req.setAttribute("errors", errors);
            	
		if(req.getSession().getAttribute("employee")==null) {
			errors.add("Please log-in to change the password.");
			return "login.jsp";
		}
    	// If user is already logged in, redirect to todolist.do
        /*if(req.getSession().getAttribute("user")==null) {
			errors.add("Please log-in to access this page.");
			return "login.jsp";
		}
        
        try {
			req.setAttribute("employeelist",employeeDAO.read());
		} catch (RollbackException e1) {
			errors.add("No User Data");
		}*/
		
		// get the userId parameter request
		int userId=0;
		try{
		userId = Integer.parseInt(req.getParameter("id"));
		System.out.println("uuu"+userId);
		} catch(NumberFormatException e) {
			errors.add("User Id not a number");
			return "ViewCustomer.do";
		}
        try {
	    	ChangePasswordForm form = formBeanFactory.create(req);
	        req.setAttribute("form",form);
	        
	        CustomerBean[] customer;
	        customer = customerDAO.match(MatchArg.equals("customerId", userId));
	        if (customer == null || customer.length <=0) {
	            errors.add("User Id not found");
	            return "ViewCustomer.do";
	        }
	        if(customer!= null && customer.length>=1) {
	        	req.setAttribute("username", customer[0]);
	        }
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	req.setAttribute("userId",userId);
	        	return "changePassword.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "changePassword.jsp";
	        }
	        
	        
	        
	        
	        
	        
	        if (customer[0].getPassword().matches((sanitize(form.getNewPassword())))) {
	        	errors.add("Please dont use old password, provide a new one");
   	        	return "changePassword.jsp";
	        }
	        
	        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
	        	errors.add("Confirm Password does not match with the new one");
   	        	return "changePassword.jsp";
	        }
	        
       		if (form.getAction().equals("changePass")) {
       			customerDAO.changePass(customer[0].getCustomerId(), form.getNewPassword());
       			String message= "Congratulations! Password changed succesfully.";
    			req.setAttribute("message", message);
    			
    			// This is email method for creating new user
	       		String emailTo;
	       		String subject;
	       		String mailMsg;	       		
	       		
	       		emailTo = sanitize(customer[0].getEmail());
	       		subject = "Your Password for Carnegie Financial Service";
	       		mailMsg = "Dear " + customer[0].getFirstName() +"&nbsp;" + customer[0].getLastName() + ",<br />";
	       		mailMsg = mailMsg + "<br />";
	       		mailMsg = mailMsg + "We have reset your password with these informations :" + "<br />";
	       		mailMsg = mailMsg + "<br />";
	       		mailMsg = mailMsg + "Username : "+ sanitize(customer[0].getUserName()) + "<br />"; 
	       		mailMsg = mailMsg + "New Password : "+ sanitize(form.getNewPassword()) + "<br />";
	       		mailMsg = mailMsg + "<br />";
	       		mailMsg = mailMsg + "Welcome back to our mutual funds group and happy investing." + "<br />";
	       		mailMsg = mailMsg + "<br />";
	       		mailMsg = mailMsg + "<br />";
	       		mailMsg = mailMsg + "Best regards,"+"<br />";
	       		mailMsg = mailMsg + "Maven Consulting Group";
	       		
	       		SendMail mailSender=new SendMail(emailTo,subject,mailMsg);
	       		mailSender.execute();
    			
    			return "success.jsp";
       		}
       		
	        // If redirectTo is null, redirect to the index page
       		String message= "Congratulations! Password changed succesfully. An email has been sent to " 
       						+ customer[0].getEmail();
			req.setAttribute("message", message);
			return "success.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "changePassword.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "changePassword.jsp";
        }
    }
}
