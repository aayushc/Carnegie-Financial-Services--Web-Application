package model;

import databean.EmployeeBean;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class EmployeeDAO extends GenericDAO<EmployeeBean> {
	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(EmployeeBean.class, tableName, cp);
	}
	
	// UserDAO read method to fetch employee data based on username
		public EmployeeBean[] matchUserName(String userName) throws RollbackException {
			EmployeeBean[] employee = match(MatchArg.equals("userName", userName));
			return employee;
		}
	
	
	// UserDAO read method to fetch employee data based on firstname
	public EmployeeBean[] matchFirstName(String firstName) throws RollbackException {
		EmployeeBean[] employee = match(MatchArg.equals("firstName", firstName));
		return employee;
	}
	
	// UserDAO read method to fetch employee data based on lastname
	public EmployeeBean[] matchLastName(String lastName) throws RollbackException {
		EmployeeBean[] employee = match(MatchArg.equals("lastName", lastName));
		return employee;
	}
	
	// EmployeeDAO create method to insert new employee to employee table
	/*public void create(EmployeeBean employee) throws RollbackException {
		try {
			Transaction.begin();

			// Create a new employee in the database
			create(employee);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}*/
	
	public void changePass(String userName, String newPassword) throws RollbackException {
        try {
        	Transaction.begin();
			EmployeeBean currentEmployee = read(userName);
			
			if (currentEmployee == null) {
				throw new RollbackException("User Name "+userName+" no longer exists");
			}
			
			currentEmployee.setPassword(newPassword);
			update(currentEmployee);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	//stephanie
	public EmployeeBean matchUsername(String username) throws RollbackException {
		EmployeeBean[] employee =match(MatchArg.equals("username", username));
		if(employee.length==0){
			return null;// TODO Auto-generated method stub
		}else {
			return employee[0];
		}
		
	}
	//aayush
	public String matchPassword(String userName) throws RollbackException {
		EmployeeBean[] employee =match(MatchArg.equals("username", userName));
		return employee[0].getPassword();
	}
}