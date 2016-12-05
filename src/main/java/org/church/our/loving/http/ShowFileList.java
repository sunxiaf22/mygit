package org.church.our.loving.http;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.church.our.loving.util.StringUtil;

/**
 * Servlet implementation class ShowFileList
 */
public class ShowFileList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String OUTPUT_DIR= StringUtil.getRootDir() + File.separator + "upload";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowFileList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		File outdir = new File(OUTPUT_DIR);
		File [] files = outdir.listFiles();
		PrintWriter printWriter = response.getWriter();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			String originalFilename = file.getName();
			String decodedFilename = URLDecoder.decode(originalFilename, "utf-8");
			printWriter.append("<p>" + (i+1) + ".  <a href =\"download?filename=" + decodedFilename + "\">" + decodedFilename +  "</a></p>" );
		}
		
		printWriter.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
