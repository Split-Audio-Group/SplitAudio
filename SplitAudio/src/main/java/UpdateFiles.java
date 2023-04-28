

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Audio_Files;
import datamodel.User;
import util.UtilDB;

/**
 * Servlet implementation class UpdateFiles
 */

public class UpdateFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFiles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String selected = null;
		String deleted = null;
		if(request.getParameter("select") == null) {
			selected = null;
		}else {
			selected = request.getParameter("select").trim();
		}
		if(request.getParameter("delete") == null) {
			deleted = null;
		}else {
			deleted = request.getParameter("delete").trim();
		}
		System.out.println("Selected: " + selected);
		System.out.println("Deleted: " + deleted);

	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      //String title = "Messages";
	      String docType = "<!doctype html>"; //
	      out.println(docType + //
	            "\r\n"
	            + "<html>\r\n"
	            + "	<head>\r\n"
	            + "		<style>\r\n"
	            + "		.loginbox p{\r\n"
	            + "			margin: 0;\r\n"
	            + "			padding: 0;\r\n"
	            + "			font-weight: bold;\r\n"
	            + "		}\r\n"
	            + "		\r\n"
	            + "		.loginbox {\r\n"
	            + "			width: 400px;\r\n"
	            + "			height: auto;\r\n"
	            + "			background: #ffffff;\r\n"
	            + "			color: #000000;\r\n"
	            + "			top: 50%;\r\n"
	            + "			left: 50%;\r\n"
	            + "			position: relative;\r\n"
	            + "			transform: translate(-50%, 2.75%);\r\n"
	            + "			box-sizing: border-box;\r\n"
	            + "			padding: 50px 50px;\r\n"
	            + "		}\r\n"
	            + "		\r\n"
	            + "		.login{\r\n"
	            + "			width: 100px;\r\n"
	            + "			height: 100px;\r\n"
	            + "			border-radius: 50%;\r\n"
	            + "			position: absolute;\r\n"
	            + "			top: -50px;\r\n"
	            + "			left: calc(50% - 50px);\r\n"
	            + "		\r\n"
	            + "		\r\n"
	            + "		}\r\n"
	            + "		\r\n"
	            + "		.loginbox input{\r\n"
	            + "			width: 100%;\r\n"
	            + "			margin-bottom: 20px;\r\n"
	            + "		}\r\n"
	            + "		\r\n"
	            + "		.loginbox input[type=\"text\"], input[type=\"password\"], input[type=\"file\"]\r\n"
	            + "		{\r\n"
	            + "			border: none;\r\n"
	            + "			border-bottom: 1px solid #909090;\r\n"
	            + "			background: transparent;\r\n"
	            + "			outline: none;\r\n"
	            + "			height: 40px;\r\n"
	            + "			color: #000000;\r\n"
	            + "			font-size: 14px;\r\n"
	            + "		}\r\n"
	            + "		\r\n"
	            + "		.loginbox input[type=\"submit\"]\r\n"
	            + "		{\r\n"
	            + "			border: none;\r\n"
	            + "			outline: none;\r\n"
	            + "			height: 40px;\r\n"
	            + "			background: #909090;\r\n"
	            + "			color: #ffffff;\r\n"
	            + "			font-size: 18px;\r\n"
	            + "			border-radius: 20px;\r\n"
	            + "		}\r\n"
	            + "		\r\n"
	            + "		.loginbox input[type=\"submit\"]:hover\r\n"
	            + "		{\r\n"
	            + "			cursor: pointer;\r\n"
	            + "			background: #909090;\r\n"
	            + "			color: #000000;\r\n"
	            + "		}\r\n"
	            + "		\r\n"
	            + "		.loginbox a{\r\n"
	            + "			text-decoration: none;\r\n"
	            + "			font-size: 12px;\r\n"
	            + "			line-height: 18px;\r\n"
	            + "			color: white;\r\n"
	            + "		}\r\n"
	            + "		\r\n"
	            + "		.loginbox a:hover\r\n"
	            + "		{\r\n"
	            + "			color: #404040;\r\n"
	            + "		}\r\n"
	            + "		.main-component {\r\n"
	            + "			margin-left: 17%;\r\n"
	            + "			padding: 20px;\r\n"
	            + "		}\r\n"
	            + "		\r\n"
	            + "		h3 {\r\n"
	            + "			font-size: 30px;\r\n"
	            + "		}\r\n"
	            + "		\r\n"
	            + "		 .input-trim {\r\n"
	            + "		 	margin: 5px;\r\n"
	            + "		 }\r\n"
	            + "		 \r\n"
	            + "		 label {\r\n"
	            + "		 	font-size: 20px;\r\n"
	            + "		 }\r\n"
	            + "		 \r\n"
	            + "		 input {\r\n"
	            + "		 	font-size: 20px;\r\n"
	            + "		 }\r\n"
	            + "	</style>\r\n"
	            + "		<meta charset=\"ISO-8859-1\">\r\n"
	            + "		<link rel=\"stylesheet\" href=\"SplitAudio.css\">\r\n"
	            + "		<title>Files</title>\r\n"
	            + "		<link rel=\"icon\" type=\"image/x-icon\" href=\"Sound_Splice_Icon.svg\">\r\n"
	            + "	</head>\r\n"
	            + "<body>\r\n"
	            + "	<header>\r\n"
	            + "		<div id=\"page_wrapper\">\r\n"
	            + "		<img src=\"Sound_Splice_Logo.svg\" height=\"200\" width=\"150\"/>\r\n"
	            + "			<nav>	\r\n"
	            + "				<ul>\r\n"
	            + "					<!--Home  Add  Edit Account-->\r\n"
	            + "					<li><a href=\"Home.html\">Home</a></li>\r\n"
	            + "					<li><a href=\"Files.html\">Files</a></li>\r\n"
	            + "					<li><a href=\"EditPage.html\">Edit</a></li>\r\n"
	            + "					<li><a href=\"Account.html\">Account</a></li>\r\n"
	            + "				</ul>\r\n"
	            + "			</nav>\r\n"
	            + "		</div>\r\n"
	            + "	</header>\r\n"
	            + "	<div id=\"page_wrapper\">");
	      out.println("");
	      
	      if(selected != null) {
	    	  Audio_Files chosenFile = UtilDB.listFiles(selected).get(0);
		      System.out.println(chosenFile.getName());
	    	  File file = new File(chosenFile.getFilepath());
	    	  UtilDB.getSession().setFile(file);
	    	  out.println("\n<div class=\"loginbox\">" 
	    			  + "<h1>"
	    			  + chosenFile.getName() + " has been successfully set as the current file."
	    			  + "</h1>"
	                  + "</div>"
	                  + "<br>"
	                  );
	      }
	      if(deleted != null) {
	    	  Audio_Files chosenFile = UtilDB.listFiles(deleted).get(0);
		      System.out.println(chosenFile.getName());
	    	  UtilDB.deleteFile(chosenFile.getId());
	    	  out.println("\n<div class=\"loginbox\">" 
	    			  + "<h1>"
	    			  + chosenFile.getName() + " has been successfully deleted."
	    			  + "</h1>"
	                  + "</div>"
	                  + "<br>"
	                  );
	      }
	      
	      out.println("");
	      out.println("</div>");
	      out.println("</body></html>");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
