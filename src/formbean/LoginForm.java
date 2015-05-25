package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean{

	private String userName;
    private String password;
    private String button;
   // private String typeButton;
    
   	/*public String getTypeButton() {
		return typeButton;
	}


	public void setTypeButton(String typeButton) {
		this.typeButton = typeButton;
	}*/


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = trimAndConvert(userName,"<>\"");
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setButton(String button) {
		this.button = button;
	}

	public String getPassword() {
		return password;
	}

	public String getButton() {
		return button;
	}
	
	public boolean isPresent()   { return button != null; }
	
	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (userName == null || userName.trim().length() == 0) errors.add("Username is required");
        if (password == null || password.trim().length() == 0) errors.add("Password is required");
        //if (typeButton == null) errors.add("Please select if you are a  customer or an employee of the company");
        if (button == null) errors.add("Button is required");
        if (errors.size() > 0) return errors;

       // if (!button.equalsIgnoreCase("Sign In")) errors.add("Invalid button");
        if (userName.matches(".*[<>\"].*")) errors.add("Username may not contain angle brackets or quotes");
		
        return errors;
    }
}
