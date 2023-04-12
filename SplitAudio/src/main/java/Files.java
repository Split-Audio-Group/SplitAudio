import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.*;
import util.*;

@WebServlet("/Files")
public class Files extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public Files() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String option = request.getParameter("options").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Messages";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      if(option.equals("my")) {
    	  User user = UtilDB.getSession().getCurrentUser();
    	  if(user == null) {
    		  out.println("Log in to see your messages <br>");
    		  out.println("<a href=/" + projectName + "/" + logIn + ">Login</a> <br>"); 
    	  }else {
    		  System.out.print("Made it to my");
    		  List<Audio_Files> listMessages = null;
 		     listMessages = UtilDB.listFilesByUser(user);
 		     
 		     display(listMessages, out);
    	  }
      }else if(option.equals("public")) {
    	  System.out.print("Made it to public");
    	  List<Audio_Files> listMessages = null;
		  listMessages = UtilDB.listFiles();
	      display(listMessages, out);
      }
      
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + home + ">Home</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Audio_Files> listMessages, PrintWriter out) {
      for (Audio_Files file : listMessages) {
         System.out.println("[DBG] " + file.getId() + ", " //
               + UtilDB.userNameById(file.getuid()) + ", " //
               + file.getName());

         out.println("<li>" + file.getId() + ", "
                 + UtilDB.userNameById(file.getuid()) + ", "
                 + file.getSize() + "</li>"
                 + "<ul><li>"
                 );
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
