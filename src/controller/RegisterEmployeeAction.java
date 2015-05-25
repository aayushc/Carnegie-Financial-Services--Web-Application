package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.*;
import formbean.*;
import model.*;
import addon.*;

public class RegisterEmployeeAction extends Action {
	private FormBeanFactory<RegisterEmployeeForm> formBeanFactory = FormBeanFactory.getInstance(RegisterEmployeeForm.class);
	
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public RegisterEmployeeAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "registerEmployee.do"; }
    
    public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession();
        
        List<String> errors= new ArrayList<String>();
		req.setAttribute("errors", errors);
            	
    	// If user is already logged in, redirect to todolist.do
		if(req.getSession().getAttribute("employee")==null) {
			errors.add("Please log-in to register new employee.");		
			return "login.jsp";		
		}	
        
       /* try {
			req.setAttribute("employeelist",employeeDAO.read());
		} catch (RollbackException e1) {
			errors.add("No User Data");
		}*/
        
        try {
	    	RegisterEmployeeForm form = formBeanFactory.create(req);
	        req.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	return "registerEmployee.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "registerEmployee.jsp";
	        }
	        
	        EmployeeBean[] employee;
	        employee = employeeDAO.matchUserName(form.getUserName());
	        
	        if (employee.length != 0) {
	        	errors.add("An employee with this username already exists, please choose a different username");
   	        	return "registerEmployee.jsp";
	        }
	        
	        CustomerBean[] customer;
	        customer = customerDAO.matchUserName(form.getUserName());
	        
	        if (customer.length != 0) {
	        	errors.add("An customer with this username already exists, please choose a different username");
   	        	return "registerEmployee.jsp";
	        }

       		if (form.getAction().equals("regEmp")) {
       			EmployeeBean newEmployee = new EmployeeBean();
       			newEmployee.setUserName(sanitize(form.getUserName()));
       			newEmployee.setFirstName(sanitize(form.getFirstName()));
       			newEmployee.setLastName(sanitize(form.getLastName()));
       			newEmployee.setEmail(sanitize(form.getEmail()));
       			newEmployee.setPassword(sanitize(form.getPassword()));
	       		employeeDAO.create(newEmployee);
	       		// This is email method for creating new user
	       		String emailTo;
	       		String subject;
	       		String message;	       		
	       		
	       		emailTo = sanitize(form.getEmail());
	       		subject = "Welcome To Carnegie Financial Service";
	       		message = "Dear " + newEmployee.getFirstName() +"&nbsp;" + newEmployee.getLastName() + ",<br />";
	       		message = message + "<br />";
	       		message = message + "We have created a new account for you with these informations :" + "<br />";
	       		message = message + "<br />";
	       		message = message + "Username : "+ sanitize(form.getUserName()) + "<br />"; 
	       		message = message + "Password : "+ sanitize(form.getPassword()) + "<br />";
	       		message = message + "<br />";
	       		message = message + "Welcome to our mutual funds group and work hard." + "<br />";
	       		message = message + "<br />";
	       		message = message + "<br />";
	       		message = message + "Best regards,"+"<br />";
	       		message = message + "Maven Consulting Group";
	       		
	       		SendMail mailSender=new SendMail(emailTo,subject,message);
	       		mailSender.execute();
	       		
	       		session.setAttribute("user", newEmployee);
	       		
	       		message= "Congratulations! You have succeed to register a new employee user. An email has been sent to " 
	       				+ sanitize(form.getEmail());
    			req.setAttribute("message", message);

	       		return("success.jsp");
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
