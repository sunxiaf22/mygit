package org.church.our.loving.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.church.our.loving.util.StringUtil;
import org.church.our.loving.util.TestMail;

/**
 * Servlet implementation class SendMail
 */
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMail() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getServletPath());
		StringWriter sw = new StringWriter();
		PrintWriter printWriter = new PrintWriter(sw);
		try {
			String type = request.getParameter("type");
			if (StringUtil.isEmpty(type)) {
				TestMail.send_email();
			} else {
				String mailto = request.getParameter("mailto");
				if (StringUtil.isEmpty(mailto)) {
					mailto = "164570618@qq.com";
				}
				String subject = request.getParameter("subject");
				if (StringUtil.isEmpty(mailto)) {
					subject = "subject - empty";
				}
				String content = request.getParameter("content");
				if (StringUtil.isEmpty(mailto)) {
					subject = "content - empty";
				}
				TestMail.commonMail(mailto, subject, content, "");
			}
			
			response.getWriter().append("Email was sent successfully !");
		} catch (AddressException e) {
			e.printStackTrace(printWriter);
		} catch (MessagingException e) {
			e.printStackTrace(printWriter);
		} catch (GeneralSecurityException e) {
			e.printStackTrace(printWriter);
		}
		response.getWriter().append(sw.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
