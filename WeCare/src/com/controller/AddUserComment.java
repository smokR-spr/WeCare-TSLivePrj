package com.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.model.Comment;

/**
 * Servlet implementation class UserComment
 */
@WebServlet("/AddUserComment")
public class AddUserComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addUserComment(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addUserComment(request,response);
	}

	private void addUserComment(HttpServletRequest request,
			HttpServletResponse response) 
			throws ServletException, IOException {
		
		/*System.out.println(request.getParameter("tid"));
		System.out.println(request.getParameter("uid"));*/
		
		Comment commentObj = new Comment(
				Integer.parseInt(request.getParameter("tid")),
				request.getParameter("uid"),
				request.getParameter("comment"), 
				new Date()
				);
		
		try {
			DataAccessObject.addUserComment(commentObj);
		} catch(Exception e) {
			request.setAttribute("msgerr", e.getMessage());
		}
		
		request.getRequestDispatcher("/TopicDetails").forward(request, response);
	}
}
