package com.banking;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchByAcno
 */
public class SearchByAcno extends HttpServlet {
	public void service(HttpServletRequest request , HttpServletResponse response) {
        HttpSession session = request.getSession();
        String rAccountNo = request.getParameter("rAccountNo");
		System.out.println(rAccountNo);
		Model m = new Model();
	    m.setrAccountNumber(rAccountNo);
	    ArrayList alSearchByAcNo = m.getMiniSbyAcNo();
	    session.setAttribute("MINISTMTBYACNO", alSearchByAcNo);
	    try {
			response.sendRedirect("ShowMiniSbyAcNo.jsp");
		} catch (IOException e) {}
	    for(Object element : alSearchByAcNo) {
	    	System.out.println(element);
	    }
	}

}
