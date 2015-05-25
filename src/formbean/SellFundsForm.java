package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundsForm extends FormBean{

	private String fundName;
	private String numberOfShares;
	private String button;
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getNumberOfShares() {
		return numberOfShares;
	}
	public void setNumberOfShares(String numberOfShares) {
		this.numberOfShares = numberOfShares;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	
	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (fundName == null || fundName.trim().length() == 0) errors.add("Fund name is required");
        double number=0.0;
        try{
        number= Double.parseDouble(numberOfShares);
        } catch (NumberFormatException e) {
        	errors.add("The entered number of shares is not a number.");
        }
        if (number < 1 ) errors.add("The number of Shares cannot be less than one");
        if (number > 1000000 ) errors.add("The number of Shares cannot be more than 1 million");
        //if(number>10000000) errors.add("Amount is more tham maximum limit, Please reduce the total amount");
        //if (button == null) errors.add("Button is required");
        double temp=number;
        if (Math.round(temp * 1000) != temp * 1000) {
			errors.add("Please enter shares upto a maximum of 3 decimal points");
		}
        if (errors.size() > 0) return errors;

        //if (!button.equals("Buy Fund")) errors.add("Invalid button");
        
        return errors;
    }
}
