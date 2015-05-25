package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.EmployeeBean;
import formbean.LoginForm;

public class LoginAction extends Action{

	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private CustomerDAO custDAO;
	private EmployeeDAO empDAO;
	
	public LoginAction(Model model) {
		custDAO= model.getCustomerDAO();
		empDAO= model.getEmployeeDAO();
	}
	
	public String getName() { return "login.do"; }

	@Override
	public String perform(HttpServletRequest req) {
		List<String> errors = new ArrayList<String>();
        req.setAttribute("errors",errors);
        
        try {
			LoginForm login= formBeanFactory.create(req);
			req.setAttribute("form",login);
			
				
			if (!login.isPresent()) {
	            return "login.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(login.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }

	        //String type=  login.getTypeButton();
	        //System.out.println(type);
	        CustomerBean customer= custDAO.getCustomerbyUsername(login.getUserName());
	        EmployeeBean employee= empDAO.matchUsername(login.getUserName());
	        if (customer == null && employee==null) {
	            errors.add("User Name not found");
	            return "login.jsp";
	        }
	        
	        if(employee== null) {
		        if(!login.getPassword().equals(custDAO.getPassword(login.getUserName()))) {
					errors.add("Password does not match");
					return "login.jsp";
				}
	        }
	        
	        if(customer== null) {
		        if(!login.getPassword().equals(empDAO.matchPassword(login.getUserName()))) {
					errors.add("Password does not match");
					return "login.jsp";
				}
	        }
	        
	        HttpSession session= req.getSession();
	        if(employee==null) {
	        	session.setAttribute("customer", customer);
	        	return "Cus_ViewAccount.do";
	        } else {
	        	session.setAttribute("employee", employee);
	        	return "ViewCustomer.do";
	        }
			
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
        	return "errors.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
        	return "errors.jsp";
		} 
	}
}
