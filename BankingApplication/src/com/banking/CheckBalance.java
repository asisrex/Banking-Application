package com.banking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckBalance
 */
public class CheckBalance extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String balanceAcn = (String) session.getAttribute("ACCOUNTNUMBER");
		System.out.println("aaa");
		Model m = new Model();
		m.setAccountNumber(balanceAcn);
	    boolean val = m.checkBalance();
		
	    if(val==true) {
		try {
			response.sendRedirect("checkBalance.jsp");
		} catch (IOException e) {}
	    }else {
	    	System.out.println("elsecheckb");
	    }
	}
}
