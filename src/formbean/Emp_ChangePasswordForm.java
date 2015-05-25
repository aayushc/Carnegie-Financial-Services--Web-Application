package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Emp_ChangePasswordForm extends FormBean {
	
	private String newPassword;
	private String confirmPassword;
	private String action;
	
	public String getNewPassword()      { return newPassword;     }
	public String getConfirmPassword()  { return confirmPassword; }
	public String getAction()    		{ return action;          }
	
	public void setNewPassword(String s)      { newPassword = s.trim();     }
	public void setConfirmPassword(String s)  { confirmPassword = s.trim(); }
	public void setAction(String s)           { action   = s;               }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		} else if (newPassword.matches(".*[<>\"].*")) {
			errors.add("New Password cannot contain angle brackets or quotes");
		} else if(newPassword.length()<5) {
			errors.add("Password cannot be less than 5 characters");
		}

		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Pwd is required");
		} else if (confirmPassword.matches(".*[<>\"].*")) {
			errors.add("Confirm Password cannot contain angle brackets or quotes");
		}
		
		if (!action.equals("changePass")) {errors.add("Invalid button");} 
		
		if (errors.size() > 0) return errors;
		
		if (!newPassword.equals(confirmPassword)) {
			errors.add("New Password and Confirm Pwd do not match");
		}		       
		
        return errors;
	}

}
