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

import org.apache.commons.lang3.StringUtils;
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
		String outputdir = request.getParameter("dir");
		String type = request.getParameter("type");
		FileInputStream fileInputStream = null;
		OutputStream os = null;
		try {
			if (null == filename) {
				System.out.println("No file name provided!");
				return;
			}
			String originalFilename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
			
			if (StringUtils.isEmpty(outputdir)) {
				outputdir = "upload";
			}
			if (StringUtils.isEmpty(type)) {
				type = "download";
			}
			String filepath = StringUtil.getRootDir() + File.separator + outputdir + File.separator;   
			filename =URLEncoder.encode(originalFilename, "utf-8");
			try {
				File targetFile = new File(filepath + filename);
				if (targetFile.exists()) {
					if ("delete".equalsIgnoreCase(type)) {
						targetFile.delete();
						request.getRequestDispatcher("/showfile").forward(request, response);
					} else {
						fileInputStream = new FileInputStream(filepath + filename); 
					}
				}
				else {
					throw new Exception("File doesn't exist!");
				}
			} catch (Exception e) {
				String error = StringUtil.processException(e);
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html");
				response.getWriter().println("<b>File does not exist any more! " + filepath + filename + "</b> <p>");
				response.getWriter().println(error);
				response.getWriter().println("</p>");
				return;
			}
			
			
			if (originalFilename.toLowerCase().endsWith(".jpg") || originalFilename.toLowerCase().endsWith(".png") || 
					originalFilename.toLowerCase().endsWith(".jpeg") || originalFilename.toLowerCase().endsWith(".gif")) {
				response.setContentType("image/png");
			}
			String userAgent = request.getHeader("user-agent");
			boolean isInternetExplorer = (userAgent.indexOf("MSIE") > -1);
			byte[] fileNameBytes = originalFilename.getBytes((isInternetExplorer) ? ("windows-1250") : ("utf-8"));
		    String dispositionFileName = "";
		    for (byte b: fileNameBytes) dispositionFileName += (char)(b & 0xff);
		    String disposition = "attachment; filename=\"" + dispositionFileName + "\"";
		    response.setHeader("Content-disposition", disposition);
			//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
			os = response.getOutputStream();
			if (null != fileInputStream) {
				int bytesRead = -1;
				while ((bytesRead=fileInputStream.read()) != -1) {  
					os.write(bytesRead);
				}   
			}
			os.flush();
		} catch (Exception e) {
			//System.out.println(StringUtil.processException(e) );
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
