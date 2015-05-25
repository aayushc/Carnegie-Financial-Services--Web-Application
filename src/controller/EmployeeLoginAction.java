package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.EmployeeDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.EmployeeBean;
import formbean.CustomerLoginForm;
import formbean.EmployeeLoginForm;

public class EmployeeLoginAction extends Action{
	private FormBeanFactory<EmployeeLoginForm> formBeanFactory = FormBeanFactory
			.getInstance(EmployeeLoginForm.class);
	
	private EmployeeDAO employeeDAO;
	
	public EmployeeLoginAction(Model model) {
		employeeDAO=model.getEmployeeDAO();// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "employeelogin.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			EmployeeLoginForm employeeform = formBeanFactory.create(request);
			request.setAttribute("form", employeeform);
		

			if (!employeeform.isPresent()) {
				return "employeelogin.jsp";
			}

			errors.addAll(employeeform.getValidationErrors());
			if (errors.size() != 0) {
				return "employeelogin.jsp";
			}

			EmployeeBean employee = employeeDAO.matchUsername(employeeform.getUserName());

			if (employee == null) {
				errors.add("The user is not found!");
				return "employeelogin.jsp";
			}

			if (!employee.checkPassword(employeeform.getPassword())) {
				errors.add("Incorrect password!");
				return "employeelogin.jsp";
			}

			HttpSession session = request.getSession();
			session.setAttribute("employee", employee);

			return "viewcustomer.do";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}


	}

}
