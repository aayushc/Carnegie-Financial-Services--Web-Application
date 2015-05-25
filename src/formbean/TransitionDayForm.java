package formbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import databean.FundPriceHistoryBean;
//import utility.AmountCheck;
//import utility.DateCheck;

public class TransitionDayForm {
	public ArrayList<FundPriceHistoryBean> prices = null;
	private ArrayList<String> sprices = null;
	public String iyear = null;
	public String imonth = null;
	public String iday = null;
	public Date date = null;
	public Date lastday = null;
	public String odate = null;
	private String button = null;

	public boolean isPresent() {
		return button != null;
	}

	@SuppressWarnings("unchecked")
	public TransitionDayForm(HttpServletRequest request) {
		button = request.getParameter("transbutton");
		prices = (ArrayList<FundPriceHistoryBean>) request
				.getAttribute("prices");
		sprices = new ArrayList<String>();
		for (FundPriceHistoryBean p : prices)
			sprices.add(request.getParameter("price_" + p.getFundId()));

		odate = request.getParameter("odate");

		lastday = (Date) request.getAttribute("lastday");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (errors.size() > 0)
			return errors;

		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println(odate);

		try {

			date = df.parse(odate);
			System.out.println("new date created");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (lastday != null && (date.before(lastday) || date.equals(lastday)))
			errors.add("New transition date should be after last transition date.");

		if (errors.size() > 0)
			return errors;

		/*
		 * for (int i = 0; i < sprices.size(); i++) { String s = sprices.get(i);
		 * if (Math.round(temp * 100) != temp * 100) {
		 * errors.add("Money value should have fewer than 2 decimals"); }
		 * errors.add("The price of fund " + prices.get(i).getFund_id() +
		 * " is empty."); else { long np = AmountCheck.checkValueString(s); if
		 * (np < 0) errors.add(AmountCheck.getErrorByCode(sprices.get(i), np));
		 * } }
		 */

		for (int i = 0; i < sprices.size(); i++) {
			String s = sprices.get(i);
			double temp = 0.0;
			try {
				temp = Double.parseDouble(s);
				if (temp < 0.01 || temp > 1000000) {
					errors.add("Price value should be less than 1 Million and more than 0.01");
					return errors;
				}
				if (Math.round(temp * 100) != temp * 100) {
					errors.add("Money value should have fewer than 2 decimals");
					return errors;
				}

			} catch (NumberFormatException e) {
				errors.add("The price entered for the share is not a number.");
				return errors;
			}
		}
		for (int i = 0; i < sprices.size(); i++) {
			String s = sprices.get(i);
			double temp = Double.parseDouble(s);

			prices.get(i).setPrice((long) (temp * 100));
			prices.get(i).setPriceDate(date);
		}
		return errors;
	}
}
