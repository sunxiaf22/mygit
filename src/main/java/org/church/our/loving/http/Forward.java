package org.church.our.loving.http;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.church.our.loving.constants.IOurChurchConstants;
import org.church.our.loving.util.StringUtil;

/**
 * Servlet implementation class Forward
 */
public class Forward extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger("Forward");
	private static String domain = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forward() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//Update Domain
		String type = request.getParameter("newdomain");
		if (!StringUtil.isEmpty(type)) {
			FileUtils.write(new File(IOurChurchConstants.NEW_DOMAIN_STORE), type, "utf-8");
			out.println("<P> Domain uploaded successfully! <p>");
			String existingDomain = FileUtils.readFileToString(new File(IOurChurchConstants.NEW_DOMAIN_STORE), "utf-8");
			domain = existingDomain;
			out.println("<p> New domain is : " + existingDomain + "</p>");
			logger.debug("New domain is : " + domain);
		}
		//Forward links
		else {
			String app = request.getParameter("app");
			if (StringUtil.isEmpty(domain)) {
				domain = FileUtils.readFileToString(new File(IOurChurchConstants.NEW_DOMAIN_STORE), "utf-8");
			} else {
				if (!StringUtil.isEmpty(app)) {
					response.sendRedirect(domain + "/" + app);
				} else {
					response.sendRedirect(domain + "/app2");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
