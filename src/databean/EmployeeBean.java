package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("userName")
public class EmployeeBean {
	
	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	
	
	 public String getUserName()     { return userName;     }
	 public String getPassword()     { return password;     }
	 public String getEmail()        { return email;        }
	 public String getFirstName()    { return firstName;    }
	 public String getLastName()     { return lastName;     }	 
	 
	 public void setUserName(String u)     { userName = u;     }
	 public void setPassword(String p)     { password = p;     }
	 public void setEmail(String e)        { email = e;        }
	 public void setFirstName(String f)    { firstName = f;    }
	 public void setLastName(String l)     { lastName = l;     }	 
	 
	 public boolean checkPassword(String password2) {
			// TODO Auto-generated method stub
			return this.password.equals(password2) ? true : false;
	}

}