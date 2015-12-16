package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.model.Event;

/**
 * Servlet implementation class GetEvent
 */
@WebServlet("/GetEvent")
public class GetEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getEvent(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getEvent(request, response);
	}
	
	private void getEvent(HttpServletRequest request,
			HttpServletResponse response) 
			throws ServletException, IOException {
		int id;
		Event event;
		String resource = "event-desc.jsp";
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			event = DataAccessObject.getEvent(id);
			request.setAttribute("event", event);
			request.getRequestDispatcher(resource).forward(request, response);
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
