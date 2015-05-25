package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

public class Emp_LogoutAction extends Action{

	public Emp_LogoutAction(Model model) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "emp_logout.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session= request.getSession();
		session.setAttribute("employee", null);
		request.setAttribute("message","You are now logged out");
		return "logout.jsp";
	}

}
