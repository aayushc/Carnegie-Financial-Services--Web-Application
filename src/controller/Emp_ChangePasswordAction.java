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

import databean.EmployeeBean;
import formbean.Emp_ChangePasswordForm;

public class Emp_ChangePasswordAction extends Action {
	
	private FormBeanFactory<Emp_ChangePasswordForm> formBeanFactory = FormBeanFactory.getInstance(Emp_ChangePasswordForm.class);
	
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public Emp_ChangePasswordAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "emp_changePassword.do"; }
	
	public String perform(HttpServletRequest req) {
        
        List<String> errors= new ArrayList<String>();
		req.setAttribute("errors", errors);
        
        try {
        	Emp_ChangePasswordForm form = formBeanFactory.create(req);
	        //req.setAttribute("form",form);
	        
	        EmployeeBean employee= (EmployeeBean)req.getSession(false).getAttribute("employee");
	        if(employee==null) {
				errors.add("Please log-in to access this page.");
				return "login.jsp";
			}
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	return "emp_changePassword.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "emp_changePassword.jsp";
	        }
	        
	        if (employee.getPassword().matches((sanitize(form.getNewPassword())))) {
	        	errors.add("Please dont use old password, provide a new one");
   	        	return "emp_changePassword.jsp";
	        }
	        
	        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
	        	errors.add("Confirm Password does not match with the new one");
   	        	return "emp_changePassword.jsp";
	        }
	        
       		if (form.getAction().equals("changePass")) {
       			employeeDAO.changePass(employee.getUserName(), form.getNewPassword());
       			String message= "Congratulations! Password changed succesfully.";
    			req.setAttribute("message", message);
    			return "success.jsp";
       		}
       		
	        // If redirectTo is null, redirect to the index page
       		String message= "Congratulations! Password changed succesfully.";
			req.setAttribute("message", message);
			return "success.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "emp_changePassword.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "emp_changePassword.jsp";
        }
    }
}
