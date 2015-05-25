package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyFundsForm extends FormBean{

	private String fund;
	private String amount;
	private String button;
	
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	
	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (fund == null || fund.trim().length() == 0) errors.add("Fund name is required");
        double amt=0.0;
        try {
        amt= Double.parseDouble(amount);
        } catch (NumberFormatException e) {
        	errors.add("The entered amount is not a number.");
        }
        if (amt < 10 ) errors.add("Your Total Amount should be greater than 10");
        if(amt>100000000) errors.add("Amount is more tham maximum limit, Please reduce the total amount");
        //if (button == null) errors.add("Button is required");
        double temp=amt;
        if (Math.round(temp * 100) != temp * 100) {
			errors.add("Please enter amount upto a maximum of 2 decimal points");
		}
        if (errors.size() > 0) return errors;

        //if (!button.equals("Buy Fund")) errors.add("Invalid button");
        
        return errors;
    }
}
