package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;

/**
 * Servlet implementation class EditTopic
 */
@WebServlet("/EditTopic")
public class EditTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		editTopic(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		editTopic(request, response);
	}
	
	private void editTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Attributes::
		 * 		edit-topic-id
		 * 		edit-topic-name
		 * 		edit_comment
		 * */
		int topicID = Integer.parseInt(request.getParameter("edit-topic-id"));
		/*String topicName = request.getParameter("edit-topic-name");*/
		String newComment = request.getParameter("edit_comment");
		
		try {
			DataAccessObject.updateTopic(topicID, newComment);
		} catch (Exception e) {
			request.setAttribute("editerr", e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/UserTopics");
		rd.forward(request, response);
	}
}
