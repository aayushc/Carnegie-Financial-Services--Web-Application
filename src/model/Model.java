package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private TransitionDateDAO transitionDateDAO;


	public Model(ServletConfig config) throws ServletException {		
		// initialise tables in my-sql database
		try {			
			String jdbcDrive 	= config.getInitParameter("jdbcDriver");
			String jdbcURL      = config.getInitParameter("jdbcURL");
			ConnectionPool cp 	= new ConnectionPool(jdbcDrive, jdbcURL);
			
			String employeeTbl = "team8_employee";
			String customerTbl = "team8_customer";
			String fundTbl="team8_fund";
			String fundPriceTbl="team8_fundPriceHistory";
			String positionTbl="team8_position";
			String transactionTbl="team8_transaction";
			String transitionDateTbl="team8_transitionDate";

			
			employeeDAO = new EmployeeDAO(cp, employeeTbl);
			customerDAO = new CustomerDAO(cp, customerTbl);
			fundDAO = new FundDAO(cp, fundTbl);
			fundPriceDAO = new FundPriceHistoryDAO(cp, fundPriceTbl);
			positionDAO = new PositionDAO(cp, positionTbl);
			transitionDateDAO = new TransitionDateDAO(cp, transitionDateTbl);			

			transactionDAO = new TransactionDAO(cp, transactionTbl, customerDAO, fundPriceDAO, positionDAO, transitionDateDAO);			
			
			
		} catch (DAOException e) {
			throw new ServletException(e);
		} 
	}
	
	public EmployeeDAO getEmployeeDAO()  { return employeeDAO; }
	public CustomerDAO getCustomerDAO()  { return customerDAO; }	
	public FundDAO getFundDAO() {return fundDAO;}
	public FundPriceHistoryDAO getFundPriceHistoryDAO() {return fundPriceDAO;}
	public TransactionDAO getTransactionDAO() {return transactionDAO;}
	public TransitionDateDAO getTransitionDateDAO() {return transitionDateDAO;}
	public PositionDAO getPositionDAO() {return positionDAO;}
}