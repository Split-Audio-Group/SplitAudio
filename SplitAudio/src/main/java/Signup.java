import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.*;

@WebServlet("/Signup")
public class Signup extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public Signup() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userName = request.getParameter("username").trim();
      System.out.print(userName);
      String password = request.getParameter("password").trim();
      System.out.print(password);
      String email = request.getParameter("email").trim();
      System.out.print(email);
      String displayName = request.getParameter("displayname").trim();
      System.out.print(displayName);
      UtilDB.createUser(userName, displayName, password, email);
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
      out.println("<li> Name: " + userName);
      out.println("<li> Display: " + displayName);
      out.println("<li> Email: " + email);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + logIn + ">Log In</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
