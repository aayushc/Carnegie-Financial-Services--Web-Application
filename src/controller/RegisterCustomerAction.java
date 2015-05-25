package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import addon.SendMail;
import databean.*;
import formbean.*;
import model.*;

public class RegisterCustomerAction extends Action {
	private FormBeanFactory<RegisterCustomerForm> formBeanFactory = FormBeanFactory.getInstance(RegisterCustomerForm.class);
	
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public RegisterCustomerAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "registerCustomer.do"; }
    
    public String perform(HttpServletRequest req) {
        //HttpSession session = req.getSession();
        
        List<String> errors= new ArrayList<String>();
		req.setAttribute("errors", errors);
            	
    	// If user is already logged in, redirect to todolist.do
        /*if(req.getSession().getAttribute("customer")==null) {
			errors.add("Please log-in to register new customer.");
			return "login.jsp";
		}
        
        try {
			req.setAttribute("customerlist",customerDAO.read());
		} catch (RollbackException e1) {
			errors.add("No Customer Data");
		}*/
        
        try {
	    	RegisterCustomerForm form = formBeanFactory.create(req);
	        req.setAttribute("form",form);
	        if(req.getSession().getAttribute("employee")==null) {
                errors.add("Please log-in to access this page.");
                    return "login.jsp";
            }
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	return "registerCustomer.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "registerCustomer.jsp";
	        }
	        
	        EmployeeBean[] employee;
	        employee = employeeDAO.matchUserName(form.getUserName());
	        
	        if (employee.length != 0) {
	        	errors.add("An employee with this username already exists, please choose a different username");
   	        	return "registerCustomer.jsp";
	        }
	        
	        CustomerBean[] customer;
	        customer = customerDAO.matchUserName(form.getUserName());
	        
	        if (customer.length != 0) {
	        	errors.add("An customer with this username already exists, please choose a different username");
   	        	return "registerCustomer.jsp";
	        }

       		if (form.getAction().equals("regCust")) {
       			CustomerBean newCustomer = new CustomerBean();
       			newCustomer.setUserName(sanitize(form.getUserName()));
       			newCustomer.setFirstName(sanitize(form.getFirstName()));
       			newCustomer.setLastName(sanitize(form.getLastName()));
       			newCustomer.setEmail(sanitize(form.getEmail()));
       			newCustomer.setAddrLine1(sanitize(form.getAddrLine1()));
       			newCustomer.setAddrLine2(sanitize(form.getAddrLine2()));
       			newCustomer.setCity(sanitize(form.getCity()));
       			newCustomer.setState(sanitize(form.getState()));
       			newCustomer.setZip(Integer.parseInt(sanitize(form.getZip())));
       			newCustomer.setPassword(sanitize(form.getPassword()));
	       		customerDAO.createAutoIncrement(newCustomer);
	       		// This is email method for creating new user
	       		String emailTo;
	       		String subject;
	       		String message;	       		
	       		
	       		emailTo = sanitize(form.getEmail());
	       		subject = "Welcome To Carnegie Financial Service";
	       		message = "Dear " + newCustomer.getFirstName() +"&nbsp;" + newCustomer.getLastName() + ",<br />";
	       		message = message + "<br />";
	       		message = message + "We have created a new account for you with these informations :" + "<br />";
	       		message = message + "<br />";
	       		message = message + "Username : "+ sanitize(form.getUserName()) + "<br />"; 
	       		message = message + "Password : "+ sanitize(form.getPassword()) + "<br />";
	       		message = message + "<br />";
	       		message = message + "Welcome to our mutual funds group and happy investing." + "<br />"; 
	       		message = message + "<br />";
	       		message = message + "<br />";
	       		message = message + "Best regards,"+"<br />";
	       		message = message + "Maven Consulting Group";
	       		
	       		SendMail mailSender=new SendMail(emailTo,subject,message);
	       		mailSender.execute();
	       		
	       		//session.setAttribute("customer", newCustomer);

	       		return("ViewCustomer.do");
       		}
	     
	        // If redirectTo is null, redirect to the index page
			return "ViewCustomer.do";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "registerEmployee.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "registerEmployee.jsp";
        }
    }
}

