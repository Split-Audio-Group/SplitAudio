import java.io.IOException;
import java.io.PrintWriter;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import datamodel.User;
import util.Info;
import util.UtilDB;



@WebServlet("/DatabaseInsert")
public class DatabaseInsert extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public DatabaseInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userName = request.getParameter("userName").trim();
      String displayName = request.getParameter("displayName").trim();
      String email = request.getParameter("email").trim();
      String password = request.getParameter("password").trim();
      UtilDB.createUser(userName, displayName, email, password);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      out.println("<li>" + userName + ", " //
     		 + userName + ", " //
              + displayName + ", " //
              + email + ", " //
              + password + "</li>");
      out.println("</ul>");
      out.println("</body></html>");
   }
   
  
  

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}