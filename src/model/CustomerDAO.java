package model;

import databean.CustomerBean;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class CustomerDAO extends GenericDAO<CustomerBean> {
	public CustomerDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerBean.class, tableName, cp);
	}
	
	// CustomerDAO read method to fetch employee data based on firstname
	public CustomerBean[] matchFirstName(String firstName) throws RollbackException {
		CustomerBean[] customer = match(MatchArg.equals("firstName", firstName));
		return customer;
	}
	
	// UserDAO read method to fetch employee data based on lastname
	public CustomerBean[] matchLastName(String lastName) throws RollbackException {
		CustomerBean[] customer = match(MatchArg.equals("lastName", lastName));
		return customer;
	}
	
	//get cash of a particular customer
	public double getCashBalance(int customerId) throws RollbackException {
		CustomerBean[] customer= match(MatchArg.equals("customerId", customerId));
		return (double)customer[0].getCash()/100;
	}
	
	//get Password
	public String getPassword(String userName) throws RollbackException {
		CustomerBean[] customer= match(MatchArg.equals("userName", userName));
		return customer[0].getPassword();
	}
	
	//get a particular customer by Username
	public CustomerBean getCustomerbyUsername(String userName) throws RollbackException {
		CustomerBean[] customer= match(MatchArg.equals("userName", userName));
		if(customer.length>0) {
			return customer[0];
		} else {
			return null;
		}
	}
	//set remaining cash after a transaction
	public void changeCashBalance(int customerId, double cash) {
		try {
			Transaction.begin();
			CustomerBean cust= read(customerId);
			System.out.println("cash"+cash);
			if(cust== null) {
				throw new RollbackException("No such customer exist.");
			}
			
			cust.setCash((long)(cash));
			update(cust);
			Transaction.commit();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// created by chang
	public void changeCashBalance2(int customerId, double cash) {
		try {
			CustomerBean cust= read(customerId);
			System.out.println("cash"+cash);
			if(cust== null) {
				throw new RollbackException("No such customer exist.");
			}
			
			cust.setCash((long)(cash));
			update(cust);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// EmployeeDAO create method to insert new employee to employee table
	/*public void create(CustomerBean customer) throws RollbackException {
		try {
			//Transaction.begin();

			// Create a new employee in the database
			create(customer);

			//Transaction.commit();
		} finally {
			//if (Transaction.isActive()) Transaction.rollback();
		}
	}*/
	
	public void changePass(int customerId, String newPassword) throws RollbackException {
        try {
        	Transaction.begin();
			CustomerBean currentCustomer = read(customerId);
			
			if (currentCustomer == null) {
				throw new RollbackException("User Id "+customerId+" no longer exists");
			}
			
			currentCustomer.setPassword(newPassword);
			update(currentCustomer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	

	// NAZ--CustomerDAO read method to fetch employee data based on username
	public CustomerBean[] matchUserName(String userName) throws RollbackException {
		CustomerBean[] customer = match(MatchArg.equals("userName", userName));
		return customer;
	}
	//end
	
	//stephanie
	public  CustomerBean matchUsername(String customername)
			throws RollbackException {
		CustomerBean[] customer = match(MatchArg.equals("customername", customername));
		if (customer.length == 0)
			return null;
		else
			return customer[0];
	}
	
	//stephanie
	public  CustomerBean matchCustomerId(int customerId)
			throws RollbackException {
		CustomerBean[] customer = match(MatchArg.equals("customerId", customerId));
		if (customer.length == 0)
			return null;
		else
			return customer[0];
	}
}
