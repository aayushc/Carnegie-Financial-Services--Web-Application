package controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import model.*;
import databean.*;
import formbean.*;

public class SearchFundAjaxAction extends Action {
	private FormBeanFactory<SearchFundForm> formBeanFactory = FormBeanFactory.getInstance(SearchFundForm.class);
	
	private FundDAO fundDAO;
	
	public SearchFundAjaxAction(Model model) {
		fundDAO = model.getFundDAO();
	}
	
	public String getName() { return "searchFundAjax.do"; }
	
	public String perform(HttpServletRequest request) {
        try {
			SearchFundForm searchForm = formBeanFactory.create(request);
			
			if (!searchForm.isPresent()) return "searchFundAjax.jsp";
			if (searchForm.getValidationErrors().size() > 0) return "searchFundAjax.jsp";
	    	
			FundBean[] fundList= null;
			
			if (searchForm.getOpt().equals("name")) {
				fundList = fundDAO.lookupStartsWithName(searchForm.getValue());
			} else if (searchForm.getOpt().equals("symbol")) {
				fundList = fundDAO.lookupStartsWithSymbol(searchForm.getValue());
			}			
			
			/*System.out.println("one of the result is: "+searchForm.getOpt());
			System.out.println("one of the result is: "+searchForm.getValue());
			System.out.println("one of the result is: "+fundList[0].getName());
			System.out.println("total result is: "+fundList.length);*/
			request.setAttribute("fundList",fundList);
			return "searchFundAjax.jsp";
		} catch (FormBeanException | RollbackException e) {
        	e.printStackTrace();
        	request.setAttribute("errors",e.getMessage());
        	return "errors.jsp";
		}
    }
}
