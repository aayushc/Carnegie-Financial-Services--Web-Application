package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerId")
public class CustomerBean {

	private int customerId;
	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private int zip;
	private long cash;
	
	public int getCustomerId()   	{ return customerId;   }
    public String getUserName()     { return userName;     }
    public String getPassword()     { return password;     }
    public String getEmail()        { return email;        }
	public String getFirstName()   	{ return firstName;    }
	public String getLastName()    	{ return lastName;     }
	public String getAddrLine1()    { return addrLine1;    }
	public String getAddrLine2()    { return addrLine2;    }
	public String getCity()    		{ return city;     	   }
	public String getState()    	{ return state;        }
	public int getZip()    			{ return zip;    	   }
	public long getCash()    		{ return cash;         }
	
	public void setCustomerId(int c)      { customerId = c;    }
	public void setUserName(String u)     { userName = u;      }
	public void setPassword(String p)     { password = p;      }
	public void setEmail(String e)        { email = e;         }
	public void setFirstName(String f)    { firstName = f;     }
	public void setLastName(String l)     { lastName = l;      }
	public void setAddrLine1(String a1)   { addrLine1 = a1;    }
	public void setAddrLine2(String a2)   { addrLine2 = a2;    }
	public void setCity(String t)     	  { city = t;          }
	public void setState(String t)     	  { state = t;          }
	public void setZip(int z)     	  	  { zip = z;      	   }
	public void setCash(long s)     	  { cash = s;      	   }
	
	public boolean checkPassword(String password) {
		return this.password.equals(password) ? true : false;
	}

	public String toString() {
		return "Customer(" + getCustomerId() + ")";
	}

	/*@Override
	public int compareTo(CustomerBean arg0) {
		// TODO Auto-generated method stub
		return 0;
	}*/

}
