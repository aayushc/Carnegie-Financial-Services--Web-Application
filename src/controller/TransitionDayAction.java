package controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import model.*;
import databean.*;
import formbean.*;

public class TransitionDayAction extends Action {
	FundDAO fundDAO;
	FundPriceHistoryDAO historyDAO;
	TransactionDAO transactionDAO;
	TransitionDateDAO dateDAO;
	TransitionDayForm tdForm;
	
	public TransitionDayAction(Model model) {
		fundDAO = model.getFundDAO();
		historyDAO = model.getFundPriceHistoryDAO();
		transactionDAO = model.getTransactionDAO();
		dateDAO = model.getTransitionDateDAO();
	}
	
	@Override
	public String getName() {
		return "e_transitionday.do";
	}

	@Override
	public String perform(HttpServletRequest request) {		
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		ArrayList<FundPriceHistoryBean> prices = new ArrayList<FundPriceHistoryBean>();
		request.setAttribute("prices", prices);
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> symbols = new ArrayList<String>();
		request.setAttribute("names", names);
		request.setAttribute("symbols", symbols );
		Date lastday = null;
		
		//for test
        HttpSession session= request.getSession();
		
		
		if (request.getSession(false).getAttribute("employee") == null){
			errors.add("Please log-in to use transition day.");
			return "login.jsp";
		}
		
		//get the price
		
		FundPriceHistoryBean[] hist;
		try {
			hist = historyDAO.getAll();
			session.setAttribute("hist", hist);

		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get the updated price and update it.
		FundBean[] funds = null;
		
		
		
		 try {
			funds = fundDAO.match();
			System.out.println(funds.length);

			lastday = dateDAO.getLastTransitionDay();
			request.setAttribute("lastday", lastday);
			System.out.println("last day got");

			// Initialize the fund information for displaying first.
			// Can also fresh the price if some other employee sets the new price.
			
			 for (FundBean f : funds) {
				if (historyDAO.getPrice(f.getFundId()) == null) {
					FundPriceHistoryBean tmp = new FundPriceHistoryBean();
					tmp.setFundId(f.getFundId());
					tmp.setPriceDate(new Date());
					tmp.setPrice(-1);
					System.out.println(tmp.getFundId());

					prices.add(tmp);
				} else
					prices.add(historyDAO.getPrice(f.getFundId()));
				System.out.println("a");
				names.add(fundDAO.read(f.getFundId()).getName());
				symbols.add(fundDAO.read(f.getFundId()).getSymbol());		
			}			
			
			tdForm = new TransitionDayForm(request);
			System.out.println("Form created");

			
			if (!tdForm.isPresent())
				return "e_transitionday.jsp";
			System.out.println("form is there");

			
			errors.addAll(tdForm.getValidationErrors());
			if (errors.size() > 0)
				return "e_transitionday.jsp";
			System.out.println("validation suceess");

			errors.addAll(transactionDAO.transitionDay(tdForm));
			if (errors.size() > 0)
				return "e_transitionday.jsp";
			System.out.println("tarnsaction success");

			lastday = dateDAO.getLastTransitionDay();
			System.out.println("got date success");

			request.setAttribute("lastday", lastday);
			System.out.println("set date success");
			
			String message;
			message="The new trading day has been set!";
			request.setAttribute("message", message);
			

		} catch (RollbackException e) {
			if (org.genericdao.Transaction.isActive())
        		org.genericdao.Transaction.rollback();
			errors.add(e.getMessage());
		}		
		
		return "e_transitionday.jsp";
	}

}
