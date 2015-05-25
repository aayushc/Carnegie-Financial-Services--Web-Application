package controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import model.Model;
import model.EmployeeDAO;

public class EmployeeLogoutAction extends Action{
	private EmployeeDAO employeeDAO;
	
	public EmployeeLogoutAction(Model model) {
		employeeDAO=model.getEmployeeDAO();// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		
		return "employeelogout.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.setAttribute("employee", null);
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		request.setAttribute("message", "You are now logged out");
		return "employeelogin.do";
	}

}
