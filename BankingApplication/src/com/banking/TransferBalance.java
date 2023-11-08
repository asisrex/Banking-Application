package com.banking;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TransferBalance
 */
public class TransferBalance extends HttpServlet {
	public void service(HttpServletRequest request , HttpServletResponse response) {
		String transferAcn = request.getParameter("transferacn");
		String transferPw = request.getParameter("transferpw");
		String transferRecieverAcn = request.getParameter("transferRecieverAcn");
		String transferAmt = request.getParameter("transferamt");
		int transferAmount = Integer.parseInt(transferAmt);
		
		LocalDateTime currentTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedTime = currentTime.format(formatter);
	    System.out.println("Current time: " + formattedTime);
	    
		Model m = new Model();
		m.setAccountNumber(transferAcn);
		m.setPw(transferPw);
		m.setrAccountNumber(transferRecieverAcn);
		m.setTransferAmount(transferAmount);
		m.setFormattedTime(formattedTime);
		
		HttpSession session = request.getSession();
		boolean setSessionVal = m.setRecieverDetailsToSession();
		
		
		if(setSessionVal == true) {
			String sessionRacn = m.getrAccountNumber();
			session.setAttribute("rACCOUNTNUMBER", sessionRacn);
		}
		
		boolean transferMoneyVal = m.transferMoney();
		if(transferMoneyVal == true) {
			try {
				response.sendRedirect("transferMoneySuccess.html");
			} catch (IOException e) {}
		}else {
			try {
				response.sendRedirect("transferMoneyFail.html");
			} catch (IOException e) {}
		}
	}
}
