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
import com.model.Topic;
import com.model.User;

/**
 * Servlet implementation class UserTopics
 */
@WebServlet("/UserTopics")
public class UserTopics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestUserTopics(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestUserTopics(request,response);
	}
	
	private void requestUserTopics(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Topic[] topics = null;
		HttpSession session = request.getSession();
		
			//System.out.println("test");
		
		try {
				//System.out.println("test2");
			topics = DataAccessObject.getTopics((User)session.getAttribute("user"));
				//System.out.println(topics);
			request.setAttribute("mytopicsArr", topics);
		} catch(Exception e) {
			/*System.out.println(e.getMessage());*/
			request.setAttribute("msgerr", e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("my-topics.jsp");
		rd.forward(request, response);
	}
}
