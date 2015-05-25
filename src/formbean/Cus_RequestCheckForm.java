package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Cus_RequestCheckForm extends FormBean {
	private String withdraw;
	
	public String getWithdraw() {
		return this.withdraw;
	}
	
	public void setWithdraw(String value) {
		this.withdraw = trimAndConvert(value, "<>\";*");
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (withdraw == null || withdraw.length() == 0) {
			errors.add("Money value is required");
		}
		if (withdraw.matches(".*[<>\"].*")) {
			errors.add("Invalid characters for money value");
		}
		Double temp = 0.0;
		try {
			temp = Double.parseDouble(withdraw);
		} catch (NumberFormatException e) {
			errors.add("The money value is invalid");
		}
		if (temp < 0.01 || temp > 10000000) {
			errors.add("Money value must be between $0.01 ~ $10,000,000.00");
		}
		
		if (Math.round(temp * 100) != temp * 100) {
			errors.add("Money value should have fewer than 2 decimals");
		}
		
		return errors;
	}
}
