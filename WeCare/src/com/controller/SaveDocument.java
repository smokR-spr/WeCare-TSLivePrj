package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * Servlet implementation class SaveDocument
 */
@WebServlet("/SaveDocument")
@MultipartConfig //to notify to the servlet that the form's encoded in multipart form
public class SaveDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_LOCATION = "serverUploads/WeCareUploads"; //relative path w.r.t current working directory

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		saveDocument(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		saveDocument(request, response);
	}

	private void saveDocument(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InputStream inFileContent = null;
		File outDir = null;
		OutputStream outFileContent = null;

		//getting the file from jsp
		//Part filePart = request.getPart("file");
		try {
			for(Part part: request.getParts()) {
				//System.out.println(part);
				String fileName = getFileName(part);
				//System.out.println(fileName);

				inFileContent = part.getInputStream();

				//System.out.println(new File("").getAbsolutePath()); //to know the current working directory
				outDir = new File(UPLOAD_LOCATION); //creates a folder in the current working directory

				//making a new directory
				if(!outDir.exists()) {
					outDir.mkdirs();
				}

				outFileContent = new FileOutputStream(outDir.getPath() + File.separator + fileName);

				//wrting the output file
				int c;
				while((c = inFileContent.read()) != -1) {
					outFileContent.write(c);
				}
			}
			
			request.setAttribute("msg", "Successfully uploaded.");
			
		} catch(Exception e) {
			//e.printStackTrace();
			response.setStatus(500); //Internal server error code
			request.setAttribute("msgerr", e.getMessage());
		} finally {
			request.getRequestDispatcher("ListUploads").forward(request, response);
			if(inFileContent != null)
				inFileContent.close();
			if(outFileContent != null)
				outFileContent.close();
		}
	}

	//to extract file name from the part
	private String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				/*String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
				 */
				return cd.substring(cd.indexOf("=") + 2, cd.length()-1);
			}
		}
		return null;
	}

}
