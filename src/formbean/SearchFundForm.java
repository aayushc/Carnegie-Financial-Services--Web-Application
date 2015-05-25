package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SearchFundForm extends FormBean {
	private String opt;
	private String value;
	
	public String getOpt() { return opt; }
	public String getValue() { return value; }
	
	public void setOpt(String s)  { opt  = s.trim(); }
	public void setValue(String s)  { value  = s.trim(); }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (opt == null) {
			errors.add("Must specify search option");
			return errors;
		}
		
		if (value == null) {
			errors.add("Must specify search keyword");
			return errors;
		}

		// Later processing wants empty strings and no null strings, so clean them up.
		if (opt == null) opt = "";
		if (value == null) value = "";
		
		if (value.length() == 0) {
			errors.add("Must specify part of keyword");
			return errors;
		}
		
		return errors;
	}
}
