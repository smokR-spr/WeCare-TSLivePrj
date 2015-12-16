package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DataAccessObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Topic;

/**
 * Servlet implementation class GetTopic
 */
@WebServlet("/GetTopic")
public class GetTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getTopic(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getTopic(request, response);
	}
	
	private void getTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getting topic ID
		int topicID = Integer.parseInt(request.getParameter("topic-id"));
		
		Topic topic = null;
		String json = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			topic = DataAccessObject.getTopic(topicID);
			
			//Jackson Project's Object to JSON conversion
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			json = mapper.writeValueAsString(topic);
				//System.out.println(json);
			response.getWriter().write(json);
		} catch (Exception e) {
			/*request.setAttribute("msgerr", e.getMessage());*/
			response.sendError(400, e.getMessage());
		}
	}
}
