package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Topic;

/**
 * Servlet implementation class SearchTopic
 */
@WebServlet("/SearchTopic")
public class SearchTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		searchTopics(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		searchTopics(request, response);
	}

	private void searchTopics(HttpServletRequest request,
			HttpServletResponse response) 
			throws ServletException, IOException {
		String searchStr = request.getParameter("search-str");
			//System.out.println(searchStr);
		
		String json = null;
		ArrayList<Topic> searchedTopics = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			searchedTopics = DataAccessObject.searchTopics(searchStr);
				//System.out.println(searchedTopics);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			json = mapper.writeValueAsString(searchedTopics);
				//System.out.println(json);
			response.getWriter().write(json);
		} catch(Exception e) {
			/*response.sendError(400, e.getMessage());*/
			response.setStatus(400);
			response.getWriter().write(e.getMessage());
		}
	}

	
}
