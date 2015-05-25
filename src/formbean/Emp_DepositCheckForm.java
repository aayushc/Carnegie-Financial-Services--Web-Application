package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Emp_DepositCheckForm extends FormBean {
	private String deposit;
	
	public String getDeposit() {
		return this.deposit;
	}
	
	public void setDeposit(String s) {
		this.deposit = trimAndConvert(s,"<>\"");;
	}
	
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (deposit == null || deposit.length() == 0) {
			errors.add("Deposit amount is required");
		}
		if (deposit.matches(".*[<>\"].*")) errors.add("You may not input angle brackets, quotes, semicolons or stars in textfields");

		double temp = 0.0;
		try {
			temp = Double.parseDouble(deposit);
		} catch(NumberFormatException e) {
			errors.add("Invalid deposit number");
		}
		
		if (temp < 10 || temp > 100000000) {
			errors.add("Money value must be between $10.00 ~ $100,000,000.00");
		}
		
		if (Math.round(temp * 100) != temp * 100) {
			errors.add("Money value should have fewer than 2 decimals");
		}
		
		
		return errors;
	}
}
