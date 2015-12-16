package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.model.Comment;
import com.model.Topic;

/**
 * Servlet implementation class TopicDetails
 */
@WebServlet("/TopicDetails")
public class TopicDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getTopicDetails(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getTopicDetails(request, response);
	}
	
	private void getTopicDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Topic topic;
		ArrayList<Comment> comments;
		
		int topicID = Integer.parseInt(request.getParameter("tid"));
			/*System.out.println("tid: " + topicID);*/
		String resource = "topic.jsp";
		
		try {
			//getting the topic
			topic = DataAccessObject.getTopic(topicID);
			comments = DataAccessObject.getComments(topicID);
			request.setAttribute("topic", topic);
			request.setAttribute("comments", comments);
		} catch (Exception e) {
			request.setAttribute("msgerr", e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(resource);
		rd.forward(request, response);
	}	
}
