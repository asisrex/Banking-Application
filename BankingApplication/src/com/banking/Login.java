package com.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response) {
		String cid = request.getParameter("cid");
		String pw = request.getParameter("pw");
		
		Model m = new Model();
		m.setCid(cid);
		m.setPw(pw);
		
		boolean val = m.login();
		
		HttpSession session=request.getSession(true);
		if(val==true) {
			String sessionAccountNumber = m.getAccountNumber();
			String sessionPassword  = m.getPw();
			int sessionBalance = m.getBalance();
			
			System.out.println(sessionAccountNumber);
			System.out.println(sessionPassword);
			System.out.println(sessionBalance);
	
			session.setAttribute("ACCOUNTNUMBER", sessionAccountNumber);
			session.setAttribute("PASSWORD", sessionPassword);
			session.setAttribute("BALANCE", sessionBalance);
			try {
				response.sendRedirect("successLogin.html");
			} catch (IOException e) {}
		}else {
			try {
				response.sendRedirect("failedLogin.html");
			} catch (IOException e) {}
		}
	}
}
