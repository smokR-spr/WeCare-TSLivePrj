package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.DataAccessObject;
import com.model.User;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		updatePassword(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		updatePassword(request, response);
	}

	private void updatePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try	{
			/*String uid = request.getParameter("uid");*/
			/*System.out.println("uid in servlet: " + uid);*/
			String oldPass = request.getParameter("old-pass");
			String newPass = request.getParameter("new-pass");
			String uid = request.getParameter("user-id");

			User user = DataAccessObject.getUser(uid);
			HttpSession session = request.getSession();

			/*User user = (User)session.getAttribute("user");*/

			/*System.out.println("!" + user.getPassword() + "!");
			System.out.println("!" + oldPass + "!");

			System.out.println(!user.getPassword().equals(oldPass));
			 */
			if(!user.getPassword().equals(oldPass)) throw new Exception("You have entered a wrong password. Please try again.");

			DataAccessObject.updatePassword(user.getuID(), newPass);
			user.setPassword(newPass);
			if(session.getAttribute("user") != null) //donot execute if the password reset is being done using the reset link sent to email
				session.setAttribute("user", user); //setting the new user id - password pair to the session to avoid relogin of the page
			/*else {
				//that is if the resetting is being done using a link
				request.setAttribute("msg", "Password updated successfully.");
				response.getWriter().write("Password updated successfully.");
				//request.getRequestDispatcher("login.jsp").forward(request, response);
				response.setHeader("Refresh", "5;url=login.jsp");
			}*/
		} catch(Exception e) {
			//System.out.println(e.getMessage());
			/*response.sendError(399, e.getMessage());*/

			response.setStatus(500); //500: INTERNAL SERVER ERROR
			PrintWriter out = response.getWriter();
			out.println(e.getMessage());
			/*e.printStackTrace();*/
			out.close();
		}
	}

}
