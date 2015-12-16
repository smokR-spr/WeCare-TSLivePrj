package com.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Misc;

/**
 * Servlet implementation class ListUploads
 */
@WebServlet(description = "To get the list of uploaded files.", urlPatterns = { "/ListUploads" })
public class ListUploads extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getUploads(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getUploads(request, response);
	}

	private void getUploads(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		File[] files = null;

		try {
			files = Misc.listFilesInDir(SaveDocument.UPLOAD_LOCATION, 'd');
			/*for(File file: files) {
				System.out.println(file);
				System.out.println(file.getName());
			}*/
			//System.out.println(files);
			request.setAttribute("uploads", files);
		} catch (Exception e) {
			response.setStatus(500); //Internal server error code
			request.setAttribute("msgerr", e.getMessage());
			//e.printStackTrace();
		}

		request.getRequestDispatcher("upload.jsp").forward(request, response);
	}

}
