package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.model.Event;

/**
 * Servlet implementation class AddEvent
 */
@WebServlet("/AddEvent")
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addEvent(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addEvent(request, response);
	}

	private void addEvent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Event event = null;
		try {
			event = new Event();
			event.setName(request.getParameter("event-name"));
			event.setVenue(request.getParameter("event-where"));
			event.setDescription(request.getParameter("event-desc"));
			
			//converting date string to Date object
			String dateStr = request.getParameter("event-when");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date time = dateFormat.parse(dateStr);
			
			event.setTime(time);
			
			DataAccessObject.saveEvent(event);
			
			request.setAttribute("msg", "Event added successfully");
			/*response.sendRedirect(request.getContextPath() + "/login-success.jsp?msg=Event+added+successfully");*/
			
		} catch(Exception e) {
			request.setAttribute("msgerr", e.getMessage());
			/*response.sendRedirect(request.getContextPath() + "/login-success.jsp?msgerr=" + e.getMessage());*/
		}
		
		request.getRequestDispatcher("/ListEvents").forward(request, response);
		// request.getHeader("referer"));
		
	}

} //servlet
