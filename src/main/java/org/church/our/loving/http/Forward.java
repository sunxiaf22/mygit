package org.church.our.loving.http;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.church.our.loving.constants.IOurChurchConstants;
import org.church.our.loving.util.StringUtil;

/**
 * Servlet implementation class Forward
 */
public class Forward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String type = request.getParameter("newdomain");
		String app = request.getParameter("app");
		if (!StringUtil.isEmpty(type)) {
			FileUtils.write(new File(IOurChurchConstants.NEW_DOMAIN_STORE), type, "utf-8");
		} else {
			String existingDomain = FileUtils.readFileToString(new File(IOurChurchConstants.NEW_DOMAIN_STORE), "utf-8");
			if (!StringUtil.isEmpty(existingDomain)) {
				if (StringUtil.isEmpty(app)) {
					response.sendRedirect(existingDomain + "/" + app);
				} else {
					response.sendRedirect(existingDomain + "/app2");
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
