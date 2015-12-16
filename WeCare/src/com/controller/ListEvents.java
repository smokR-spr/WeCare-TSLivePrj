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
import com.model.Event;

/**
 * Servlet implementation class ListEvents
 */
@WebServlet("/ListEvents")
public class ListEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listEvents(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listEvents(request, response);
	}

	private void listEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Event> eventsList = null;
		/*String resource = null;*/
		
		//getting the topics from the DB
		try {
			eventsList = DataAccessObject.getEvents(0); //to get all events pass arg as '0'
			
			request.setAttribute("eventslist", eventsList);
		} catch(Exception e) {
			request.setAttribute("msgerr", e.getMessage());
		}
		
		//dispatching the request to list-events.jsp page
		RequestDispatcher rd = request.getRequestDispatcher("/list-events.jsp");
		rd.forward(request, response);
	}
}
