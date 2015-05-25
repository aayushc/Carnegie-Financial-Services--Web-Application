package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Emp_CreateFundForm extends FormBean {
	private String fundName;
	private String fundSymbol;
	
	public String getFundName() {
		return fundName;
	}
	
	public void setFundName(String fundName) {
		this.fundName = trimAndConvert(fundName, "<>\"");
	} 
	
	public String getFundSymbol() {
		return this.fundSymbol;
	}
	
	public void setFundSymbol(String fundSymbol) {
		this.fundSymbol = trimAndConvert(fundSymbol, "<>\"");
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundName == null || fundName.length() == 0) {
			errors.add("Fund name is missing");
		}

		if (fundSymbol == null || fundSymbol.length() == 0) {
			errors.add("Fund symbol is missing");
		}
		
		if (!fundName.matches("[0-9a-zA-Z\\s]+")) {
            errors.add("Input must not contain special characters.");
        }
		
		if (!fundSymbol.matches("[0-9a-zA-Z]{1,5}")) {
            errors.add("Fund symbol's length must be 1~5.");
        }
		
		if (fundName.matches(".*[<>\"].*")) errors.add("Invalid characters in Fund Name textfields");
		if (fundSymbol.matches(".*[<>\"].*")) errors.add("Invalid characters in Ticker textfields");
		return errors;
	}
}
