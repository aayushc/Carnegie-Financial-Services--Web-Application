package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.CustomerDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import formbean.CustomerLoginForm;


public class CustomerLoginAction extends Action {

	private FormBeanFactory<CustomerLoginForm> formBeanFactory = FormBeanFactory
			.getInstance(CustomerLoginForm.class);

	private CustomerDAO customerDAO;

	public CustomerLoginAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "customerlogin.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			CustomerLoginForm customerform = formBeanFactory.create(request);
			 request.setAttribute("form",customerform);
			//request.setAttribute("customerList", customerDAO.getCustomers());

			if (!customerform.isPresent()) {
				return "customerlogin.jsp";
			}

			errors.addAll(customerform.getValidationErrors());
			if (errors.size() != 0) {
				return "customerlogin.jsp";
			}

			CustomerBean customer = customerDAO.matchUsername(customerform.getUsername());

			if (customer == null) {
				errors.add("The user is not found!");
				return "customerlogin.jsp";
			}

			if (!customer.checkPassword(customerform.getPassword())) {
				errors.add("Incorrect password!");
				return "customerlogin.jsp";
			}

			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);

			return "Cus_ViewAccount.do";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}


	}

}
