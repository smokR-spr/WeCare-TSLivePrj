package com.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.data.EmailUtility;
import com.model.User;

/**
 * Servlet implementation class SendResetPasswordLink
 */
@WebServlet("/SendResetPasswordLink")
public class SendResetPasswordLink extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String host;
	private String port;
	private String admin;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		admin = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		sendLink(request, response);
	}

	private void sendLink(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resetUID = request.getParameter("reset-uid");
			//System.out.println(resetUID);
		User user = null;
		String mailStatus = "";

		try {
			user = DataAccessObject.getUser(resetUID); //throws exception
			int token = DataAccessObject.getResetToken(resetUID);
			String message = "";
			String subject = "Reset Password - We Care";
			String recipient = user.getEmail();

			String url = request.getRequestURL().toString();
			String uri = request.getRequestURI();
			String ctx = request.getContextPath();
			String base = url.substring(0, url.length() - uri.length()) + ctx;
			//System.out.println(base);

			message = "<center><h1>We Care</h2>\n";
			message += "<h2>Reset Password</h2></center>\n";
			message += "<p><a href=\"" + base + "/ResetPassword?t=" + token + "\">Click here</a> to reset your password or go to the URL given below:</p>";
			message += "<p>" + base + "/ResetPassword?t=" + token + "</p>";
			
			EmailUtility.sendEmail(host, port, admin, pass, recipient, subject, message);
			mailStatus = "The reset link is sent to your registered email id. The link expires in 24hrs.";
		} catch(Exception e) {
			e.printStackTrace();
			response.setStatus(500); //Internal server error code
			mailStatus = e.getMessage();
		} finally {
			response.getWriter().write(mailStatus);
		}


	}

}
