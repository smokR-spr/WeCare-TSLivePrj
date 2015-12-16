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
 * Servlet implementation class DeleteTopic
 */
@WebServlet("/DeleteTopic")
public class DeleteTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteTopic(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteTopic(request, response);
	}
	
	void deleteTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getting the topic ID from request
		int topicID = Integer.parseInt(request.getParameter("topic-id"));
		String resource = "my-topics.jsp";
		
		//passing the id to DAO
		try {
			DataAccessObject.deleteTopic(topicID);
			
			//##### if admin
			HttpSession session = request.getSession();
			if(((User)session.getAttribute("user")).getuID().equals("rahul.admin")) {
				resource="/ListTopics";
			} 
			//#### admin closed
			else { 
				resource  = "/UserTopics";
			}
		} catch(Exception e) {
			request.setAttribute("msgerr", e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(resource);
		rd.forward(request, response);
	}

}
