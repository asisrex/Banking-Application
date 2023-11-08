package com.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ForgotPassword
 */
public class ForgotPassword extends HttpServlet {
	public void service(HttpServletRequest request , HttpServletResponse response) {
		String resetNPW = request.getParameter("resetNPW");
		String accountNumber = request.getParameter("acn");
		Model m = new Model();
		m.setPw(resetNPW);
		m.setAccountNumber(accountNumber);
boolean changePasswordVAL = m.changePassword();
        
        if(changePasswordVAL == true) {
        	System.out.println("changepassword servlet if");
					try {
						response.sendRedirect("passwordChangeSuccessfull.html");
					} catch (IOException e) {}
				}else {
					try {
						response.sendRedirect("passwordChangeFailed.html");
					} catch (IOException e) {}
				}
			} 
	}


