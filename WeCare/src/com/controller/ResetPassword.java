package com.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.data.Misc;
import com.model.Reset;
import com.model.User;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//getting the parameters from the reset password link from the mail
		int token = Integer.parseInt(request.getParameter("t")); //t = unique token generated
			//System.out.println(token);
		
		try {
			Reset resetDetails = DataAccessObject.getResetDetails(token);
			
			if(!Misc.isWithin24Hrs(new Date(), resetDetails.getResetTime())) {
				throw new Exception();
			}
			User user = DataAccessObject.getUser(resetDetails.getUid());
					System.out.println(user.getuID());
			request.setAttribute("resetuser", user);
			request.getRequestDispatcher("reset.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			//response.getWriter().write(e.getMessage());
			response.getWriter().write("The reset password link has expired. Please try again.");
			//StackTraceElement[] eEles = e.getStackTrace();
				//System.out.println(eEles[0].getMethodName());
			//if(eEles[0].getMethodName() == "getResetDetails") {
				/*try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}*/
			
				response.getWriter().append("\nRedirecting to login page...");
				response.setHeader("Refresh", "5;url=login.jsp");
				//response.sendRedirect("login.jsp");
			//}
		}
		
	}

}
