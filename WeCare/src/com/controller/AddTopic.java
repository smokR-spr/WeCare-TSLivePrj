package com.controller;

import java.io.IOException;
import java.util.Date;

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
 * Servlet implementation class AddTopic
 */
@WebServlet("/AddTopic")
public class AddTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addTopic(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addTopic(request, response);
	}
	
	void addTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getting the values from the login-success.jsp
		HttpSession session = request.getSession();
		String topicName = request.getParameter("topic-name");
		String comment = request.getParameter("comment");
		Date date = new Date();
		User user = (User)session.getAttribute("user");
		
		Topic topic = new Topic();
		
		topic.setTopicName(topicName);
		topic.setComment(comment);
		topic.setUID(user.getuID());
		topic.setDate(date);
		
		try {
			DataAccessObject.saveTopic(topic);
			request.setAttribute("msg", "Topic added successfully");
		} catch(Exception e) {
			request.setAttribute("msgerr", e.getMessage()); 
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("UserTopics");
		rd.forward(request, response);
	}
}
