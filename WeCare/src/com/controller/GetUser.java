package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetUser
 */
@WebServlet(description = "Implements the servlet for getting user details.", urlPatterns = { "/GetUser" })
public class GetUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		
		/*System.out.println("GetUser");
		System.out.println(userId);*/
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			//request.setAttribute("userDetails", DataAccessObject.getUser(userId));
			//Jackson Project's Object to JSON conversion
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			json = mapper.writeValueAsString(DataAccessObject.getUser(userId));
				//System.out.println(json);
			response.getWriter().write(json);
		} catch(Exception e) {
			//System.out.println("GetUser error");
			//e.printStackTrace();
			response.setStatus(500); //Internal server error code
			response.getWriter().write(e.getMessage());
		}
		
		//request.getRequestDispatcher("forgot-pass.jsp").forward(request, response);
		
	}

}
