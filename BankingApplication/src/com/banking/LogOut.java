package com.banking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOut
 */
public class LogOut extends HttpServlet {

	public void service(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null || session.getAttribute("cid") == null) {
		    session.invalidate();
		    try {response.sendRedirect("index.html");}catch (IOException e) {}
		}

		}

	}

