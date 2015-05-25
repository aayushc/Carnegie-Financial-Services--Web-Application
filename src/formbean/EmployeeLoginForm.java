package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class EmployeeLoginForm extends FormBean {
	private String username;
	private String password;

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = trimAndConvert(username,"<>\"");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("username is required!");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required!");
		}

		return errors;
	}

}
