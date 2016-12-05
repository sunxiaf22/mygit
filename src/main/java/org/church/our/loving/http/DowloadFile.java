package org.church.our.loving.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.church.our.loving.util.StringUtil;

/**
 * Servlet implementation class DowloadFile
 */
public class DowloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DowloadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		String filename = request.getParameter("filename"); 
		FileInputStream fileInputStream = null;
		OutputStream os = response.getOutputStream();
		try {
			if (null == filename) {
				System.out.println("No file name provided!");
				return;
			}
			filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
			if (filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".png") || 
					filename.toLowerCase().endsWith(".jpeg") || filename.toLowerCase().endsWith(".gif")) {
				response.setContentType("image/png");
			}
			response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
			filename =URLEncoder.encode(filename, "utf-8");
		    String filepath = StringUtil.getRootDir() + File.separator + "upload" + File.separator;   
			File downloadFile = new File(filepath);
			if (!downloadFile.exists()) {
				System.out.println("No file found!");
			}
			fileInputStream = new FileInputStream(filepath + filename);  
			if (null != fileInputStream) {
				int bytesRead = -1;
				while ((bytesRead=fileInputStream.read()) != -1) {  
					os.write(bytesRead);
				}   
			}
			os.flush();
		} catch (Exception e) {
			System.out.println(StringUtil.processException(e) );
		} finally {
			if (null != fileInputStream ) {
				fileInputStream.close();   
			}
			if (null != os) {
				os.close();
			}
		}
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
