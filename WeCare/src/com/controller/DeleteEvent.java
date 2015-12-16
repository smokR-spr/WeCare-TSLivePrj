package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;

/**
 * Servlet implementation class DeleteEvent
 */
@WebServlet("/DeleteEvent")
public class DeleteEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteEvents(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteEvents(request,response);
	}

	private void deleteEvents(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*String[] eventIDs = request.getParameterValues("event-delete-cb");

		for(String eventID : eventIDs) {
			//System.out.println(event);*/

		int id = Integer.parseInt(request.getParameter("id"));
		try {
			DataAccessObject.deleteEvent(id);
		} catch(Exception e) {
			request.setAttribute("msgerr", e.getMessage());
		}
		
		request.getRequestDispatcher("ListEvents").forward(request, response);

	}

}
