package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.model.Topic;

/**
 * Servlet implementation class ListTopics
 */
@WebServlet("/ListTopics")
public class ListTopics extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listTopics(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listTopics(request, response);
	}
	
	private void listTopics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Topic> topics = null;
		/*String resource = null;*/
		
		//getting the topics from the DB
		try {
			topics = DataAccessObject.getTopics();
			
			
			//sorting in decending order of topicIDs
			Collections.sort(topics, new Comparator<Topic>() {
				public int compare(Topic t1, Topic t2) {
					return t2.getTopicID() - t1.getTopicID();
				}
			});
			
			request.setAttribute("topics", topics);
		} catch(Exception e) {
			request.setAttribute("msgerr", e.getMessage());
		}
		
		//dispatching the request to list-topics.jsp page
		RequestDispatcher rd = request.getRequestDispatcher("/list-topics.jsp");
		rd.forward(request, response);
	}

}
