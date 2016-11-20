package org.church.our.loving.http;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.church.our.loving.constants.IOurChurchConstants;
import org.church.our.loving.util.FileUtils;
import org.church.our.loving.util.Logger;
import org.church.our.loving.util.StringUtil;

public class DetectIPServ extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = new Logger(1);
	private String rootDir = "";
	/**
	 * Constructor of the object.
	 */
	public DetectIPServ() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/javascript");
		PrintWriter out = response.getWriter();
		StringBuffer jsoncontent = new StringBuffer();
		try {
			String userName = request.getParameter("username");
			String userPwd = request.getParameter("password");
			String forwardLink = request.getParameter("forward");
			String jsoncallback = request.getParameter("jsoncallback");
			jsoncontent.append(jsoncallback + "({");
			jsoncontent.append("\"date\":\"" + StringUtil.formateDateToString(new Date(), "yyyy-MM-dd HH:mm") + "\",");
			if ("admin".equalsIgnoreCase(userName) && "pwd".equalsIgnoreCase(userPwd)) {
				if (null != forwardLink) {
					FileUtils.writeToFile(IOurChurchConstants.FORWARD_FILE, forwardLink, false);
				}
				String ipAddr = request.getRemoteAddr();
				File ouputfile = null;
				String latestIP = StringUtil.getLatestIP();
				if (!ipAddr.equalsIgnoreCase(latestIP)) {
					String fileContent ="\n" + ipAddr + " | " + StringUtil.formateDateToString(new Date(), IOurChurchConstants.NORMAL_DATE_FORMAT);
					ouputfile =  FileUtils.writeToFile(IOurChurchConstants.IP_FILE, fileContent, true);
					//out.println("<br/> IP: [" + ipAddr + "] was successfully updated into : " + (ouputfile == null? "null" : ouputfile.getAbsolutePath()));
					jsoncontent.append("\"IP\":\"" + ipAddr + "\",");
				}
				jsoncontent.append("\"EXISTINGIP\":\"" + latestIP + "\",");
				jsoncontent.append("\"PATH\":\"" + (ouputfile == null? "null" : ouputfile.getAbsolutePath()) + "\",");
			} else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong Link ^_^!!! Please come back later!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e); 
		} finally{
			jsoncontent.append("\"end\":\"end\"");
			jsoncontent.append("});");
			out.println(jsoncontent.toString());
			out.flush();
			out.close();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		rootDir = StringUtil.getRootDir();
		logger.debug(rootDir);
	}

}
