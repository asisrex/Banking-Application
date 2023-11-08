package com.banking;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class TransferMoneyFilter
 */
public class TransferMoneyFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TransferMoneyFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String transferAccountNumber = request.getParameter("transferacn");
		String transferPassword = request.getParameter("transferpw");
		String transferReceiverAccountNumber = request.getParameter("transferRecieverAcn");
		String tfa = request.getParameter("transferamt");
		int transferAmount = Integer.parseInt(tfa);
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		 
		String sessionAccountNumber = (String) session.getAttribute("ACCOUNTNUMBER");
	    String sessionPassword = (String) session.getAttribute("PASSWORD");
		String sessionRAccountNumber = (String) session.getAttribute("rACCOUNTNUMBER");
		int sessionBalance = (int) session.getAttribute("BALANCE");
		
		if(sessionBalance > transferAmount)
		{chain.doFilter(request, response);}
		else {
			((HttpServletResponse) response).sendRedirect("errorTransferMoney.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
