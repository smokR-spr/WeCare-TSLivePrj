package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadDocument
 */
@WebServlet("/DownloadDocument")
public class DownloadDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		downloadDoc(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		downloadDoc(request, response);
	}

	private void downloadDoc(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("f");
		//System.out.println(fileName);
		File fileOnServer = new File(SaveDocument.UPLOAD_LOCATION + File.separator + fileName);
		InputStream in = new FileInputStream(fileOnServer);

		//getting the writer from response and writing data onto it
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int length;
		while((length = in.read(buffer)) > 0) {
			out.write(buffer, 0 , length);
		}

		in.close();
		out.flush();
	}
}
