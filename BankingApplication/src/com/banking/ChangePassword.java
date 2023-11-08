package com.banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.jdbc.OracleDriver;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	
	public void service(HttpServletRequest request,HttpServletResponse response) {
		String newPw = request.getParameter("npw");
 
		HttpSession session = request.getSession();
        String sessionAcn = (String) session.getAttribute("ACCOUNTNUMBER");

       
        Model m = new Model();
        m.setPw(newPw);
        m.setAccountNumber(sessionAcn);
        
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


