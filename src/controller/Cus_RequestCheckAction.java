package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.TransactionBean;
import formbean.Cus_RequestCheckForm;
import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

public class Cus_RequestCheckAction extends Action {
	private CustomerDAO customerDAO;
	private TransactionDAO transDAO;
	private FormBeanFactory<Cus_RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(Cus_RequestCheckForm.class);

	public Cus_RequestCheckAction(Model model) {
		this.customerDAO = model.getCustomerDAO();
		this.transDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		return "cus_requestCheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			Cus_RequestCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (request.getSession().getAttribute("customer") != null) {
				CustomerBean cus = (CustomerBean) request.getSession()
						.getAttribute("customer");
				System.out.println(cus.getFirstName());
			}
			if (request.getSession().getAttribute("customer") == null) {
				errors.add("Please log-in to access this page.");
				return "login.jsp";
			}
			CustomerBean customer = (CustomerBean) request.getSession(false)
					.getAttribute("customer");
			if (customer == null) {
				return "login.jsp";
			}

			customer = customerDAO.read(customer.getCustomerId());
			if (customer == null) {
				return "login.jsp";
			}

			request.setAttribute("customer", customer);
			request.setAttribute("cash", ((double) customer.getCash()) / 100);

			if (!form.isPresent()) {
				return "requestCheck.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "requestCheck.jsp";
			}

			double amount = Double.parseDouble(form.getWithdraw());

			Transaction.begin();
			customer = customerDAO
					.getCustomerbyUsername(customer.getUserName());
			if ((double) customer.getCash() / 100 < amount) {
				if (Transaction.isActive())
					Transaction.rollback();
				errors.add("Withdraw amount requested cannot exceed your current balance!");
				return "requestCheck.jsp";
			}

			customerDAO.changeCashBalance2(customer.getCustomerId(),
					(customer.getCash() - amount * 100));

			TransactionBean trans = new TransactionBean();
			trans.setAmount((long) (amount * 100));
			trans.setCustomerId(customer.getCustomerId());
			trans.setExecuteDate(null);
			trans.setTransactionType("WITHDRAW");
			trans.setStatus("PENDING");
			if (Transaction.isActive()) {
				Transaction.commit();
			}
			customer = customerDAO
					.getCustomerbyUsername(customer.getUserName());

			request.getSession().setAttribute("customer", customer);
			request.setAttribute("cash", ((double) customer.getCash()) / 100);

			DecimalFormat nf = new DecimalFormat("###,###,###,###,##0.00");
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);

			transDAO.create(trans);

			request.setAttribute("message",
			"Your request is being processing");
			return "requestCheck.jsp";

		} catch (FormBeanException e) {
			if (Transaction.isActive())
				Transaction.rollback();
			errors.add(e.getMessage());
			return "requestCheck.jsp";
		} catch (RollbackException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			errors.add(e.getMessage());
			return "requestCheck.jsp";
		}

	}

}
