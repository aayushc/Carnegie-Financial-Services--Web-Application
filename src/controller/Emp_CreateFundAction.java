package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.EmployeeBean;
import databean.FundBean;
import formbean.Emp_CreateFundForm;
import model.FundDAO;
import model.Model;

public class Emp_CreateFundAction extends Action {
	private FundDAO fundDAO;
	private FormBeanFactory<Emp_CreateFundForm> formBeanFactory = FormBeanFactory
			.getInstance(Emp_CreateFundForm.class);

	@Override
	public String getName() {
		return "emp_createFund.do";
	}

	public Emp_CreateFundAction(Model model) {
		this.fundDAO = model.getFundDAO();
	}
	
	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			EmployeeBean employee = (EmployeeBean) request.getSession(false)
					.getAttribute("employee");
			if (employee == null) {
				return "login.jsp";
			}

			Emp_CreateFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) {
				// !!!!!!do the jsp
				return "createFund.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "createFund.jsp";
			}
			
			
			Transaction.begin();
			FundBean fund = fundDAO.getFundByName(form.getFundName());
			if (fund != null) {
				errors.add("Fund name" + form.getFundName() + " is in use");
				if (Transaction.isActive()) {
					Transaction.rollback();
				}
				return "createFund.jsp";
			}
			
			fund = fundDAO.getFundBySymbol(form.getFundSymbol());
			if (fund != null) {
				errors.add("Fund ticker " + form.getFundSymbol() + " is in use");
				if (Transaction.isActive()) {
					Transaction.rollback();
				}
				return "createFund.jsp";
			}
			
			fund = new FundBean();
			fund.setName(form.getFundName());
			fund.setSymbol(form.getFundSymbol());
			fundDAO.createAutoIncrement(fund);
			if (Transaction.isActive())
				Transaction.commit();
			request.setAttribute("fund", fund);
			request.setAttribute("message", "Fund " + fund.getName() + " is created");
			// !!!!should be a sucess page or just this
			return "success.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "createFund.jsp";
		} catch (RollbackException e) {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
			errors.add(e.getMessage());
			return "createFund.jsp";
		}

	}

}
