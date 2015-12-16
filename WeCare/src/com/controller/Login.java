package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.DataAccessObject;
import com.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		login(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		login(request,response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message;
				
		String uID = request.getParameter("user-id");
		String password = request.getParameter("password");
		
		User user = null;
		HttpSession session = null;
		
		String resource = "/login.jsp";
		try {
			user = DataAccessObject.getUser(uID);
			
			//validation of password
			if(!password.equals(user.getPassword())) {
				throw new Exception("Incorrect password. Please try again.");
			} else {
				//creating a session and setting the attributes
				session = request.getSession(true);
				session.setMaxInactiveInterval(1800);//takes seconds as argument
				resource = "/login-success.jsp";
				/*message = "Welcome " + user.getUID() + "!";
				request.setAttribute("msg", message);*/
			}
			
		} catch(Exception e) {
			message = e.getMessage();
			e.printStackTrace();
			request.setAttribute("msgerr", message);
			request.setAttribute("msg", "");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(resource);
		/*request.setAttribute("user-id", uID);*/
		if(session != null && user != null) {
			session.setAttribute("user", user);
		}
		rd.forward(request, response);
	}
}
