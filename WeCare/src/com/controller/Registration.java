package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.model.User;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String realPath;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		registerUser(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		registerUser(request, response);
	}
	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declarations
		RequestDispatcher rd;
		String message = null;
		String resource = "signup.jsp";
		//to get the real path of the images folder
		realPath = this.getServletContext().getRealPath("/images/user_img_default.png");
		//System.out.println(realPath);
		int incompleteCnt = 0;
		String uName = request.getParameter("user-name");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("re-password");
		/*String securityQuestion = request.getParameter("sec-q");
		String securityAnswer = request.getParameter("sec-a");*/
		String uID = request.getParameter("user-id");
		String city = request.getParameter("city");
		String gender = request.getParameter("sex");
		String email = request.getParameter("email");
		/*System.out.println(email + "\n" + securityQuestion + "\n" + securityAnswer);*/
		
		//Validation - form completion
		message = "Please enter";
		if(uName == null || uName.equals("")) {
			message += " Name";
			incompleteCnt++;
		}
		if(city == null || city.equals("")) {
			if(incompleteCnt > 0) message += ",";
			message += " City";
			incompleteCnt++;
		}
		if(uID == null || uID.equals("")) {
			if(incompleteCnt > 0) message += ",";
			message += " User ID";
			incompleteCnt++;
		}
		if(password == null || password.equals("") || rePassword == null || rePassword.equals("")) {
			if(incompleteCnt > 0) message += ",";
			message += " Password(s)";
			incompleteCnt++;
		}
		
		//register operation
		if(incompleteCnt == 0) {
			//creating user object with the above validated values
			User user = new User();
			
			try {
				if(!password.equals(rePassword)) {
					throw new Exception("The entered passwords donot match. Please try again.");
				} else {
					user.setPassword(password);
				}
				user.setuID(uID);
				user.setName(uName);
				user.setGender(gender);
				user.setCity(city);
				/*user.setSecurityQuestion(securityQuestion);
				user.setSecurityAnswer(securityAnswer);*/
				user.setEmail(email);
							
				DataAccessObject.save(user);
				resource = "login.jsp";
				message = "Registered successfully. Please log in.";
			} catch(Exception e) {
				message = e.getMessage();
			}
		}
		
		rd = request.getRequestDispatcher(resource);
		request.setAttribute("uid", uID);
		request.setAttribute("uname", uName);
		request.setAttribute("sex", gender);
		request.setAttribute("city", city);
		request.setAttribute("msg", message);
		request.setAttribute("email", email);
		rd.forward(request, response);
	}
}
