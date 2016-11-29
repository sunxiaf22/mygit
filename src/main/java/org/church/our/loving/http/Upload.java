package org.church.our.loving.http;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.church.our.loving.util.StringUtil;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("<br/>");
		//File repository = new File(StringUtil.getRootDir());
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			response.getWriter().append("Has file upload request.").append("<br/>");
			if (isMultipart) {
				// Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				// Configure a repository (to ensure a secure temp location is used)
				//response.getWriter().append(repository.getAbsolutePath()).append("<br/>");
				//factory.setRepository(repository);
				
				ProgressListener progressListener = new ProgressListener(){
					   public void update(long pBytesRead, long pContentLength, int pItems) {
					       System.out.println("We are currently reading item " + pItems);
					       if (pContentLength == -1) {
					    	   System.out.println("So far, " + pBytesRead + " bytes have been read.");
					       } else {
					    	   System.out.println("So far, " + pBytesRead + " of " + pContentLength
					                              + " bytes have been read.");
					       }
					   }
			   };
				
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setProgressListener(progressListener);
				
				// Parse the request
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {
						response.getWriter().append("===================INPUTS================").append("<br/>");
						response.getWriter().append("Field Name: " + item.getFieldName() + " - Value : " + item.getString()).append("<br/>");
					}else {
						response.getWriter().append("===================FILE================").append("<br/>");
					    String fileName = item.getName();
					    String contentType = item.getContentType();
					    boolean isInMemory = item.isInMemory();
					    long sizeInBytes = item.getSize();
						response.getWriter().append("File: " + fileName +  " - contentType : " + contentType + " isInMemory:" + isInMemory + " sizeInBytes : "+ sizeInBytes ).append("<br/>");
						String uploadDirStr = getServletContext().getRealPath("") + File.separator + "upload"; 
						File uploadDir = new File(uploadDirStr);
						if (!uploadDir.exists()) {
							uploadDir.mkdirs();
						}
						item.write(new File(uploadDirStr + File.separator +  fileName));
						response.getWriter().append("<a href =\"upload/" +  fileName + "\"> donwload file</a>");
					}
				}
			}
			
		} catch (Exception e) {
			response.getWriter().append(StringUtil.processException(e));
		} finally {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
