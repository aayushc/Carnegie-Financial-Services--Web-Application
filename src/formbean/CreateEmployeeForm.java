package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateEmployeeForm extends FormBean {
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String confirmPassword;
	
	public String getUserName()  { return userName;  }
	public String getFirstName() { return firstName; }
	public String getLastName()  { return lastName;  }
	public String getEmail()     { return email;     }
	public String getPassword()  { return password;  }
	public String getConfirmPassword()  { return confirmPassword; }
	
	public void setUserName(String s)         { userName = s.trim();  }
	public void setFirstName(String s)        { firstName = s.trim(); }
	public void setLastName(String s)         { lastName = s.trim();  }
	public void setEmail(String s)            { email = s.trim();     }
	public void setPassword(String s)         { password = s.trim();  }
	public void setConfirmPassword(String s)  { confirmPassword = s.trim(); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		} else if (userName.matches(".*[<>\"].*")) {
			errors.add("User Name cannot contain angle brackets or quotes");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		} else if (password.matches(".*[<>\"].*")) {
			errors.add("New Password cannot contain angle brackets or quotes");
		} else if(password.length()<5) {
			errors.add("Password cannot be less than 5 characters");
		}

		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Pwd is required");
		} else if (confirmPassword.matches(".*[<>\"].*")) {
			errors.add("New Password cannot contain angle brackets or quotes");
		}
		
		if (errors.size() > 0) return errors;
		
		if (!password.equals(confirmPassword)) {
			errors.add("Password and Confirm Pwd do not match");
		}
		
		return errors;
	}
}
