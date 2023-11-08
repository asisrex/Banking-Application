package com.banking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MiniStatement
 */
public class MiniStatement extends HttpServlet {
	PrintWriter pw;
	public void service(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String fromAcNo = (String) session.getAttribute("ACCOUNTNUMBER");
		
		ArrayList miniAL = new ArrayList();
		Model m = new Model();
		m.setAccountNumber(fromAcNo);
		m.viewMiniStatement();
		miniAL=m.getAl();
		
		/*String w = "Your AccN";
		String x = "Receiver AccN";
		String y = "Amount";
		String z = "Date & Time";*/
			
	        PrintWriter pw;
			try {
				pw = response.getWriter();
				for(Object element : miniAL) {
					pw.println(element+" ");
				}	        
			} catch (IOException e) {e.printStackTrace();} 
		
	}
}
