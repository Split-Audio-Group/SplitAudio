import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.*;
import util.Info;
import util.UtilDB;

@WebServlet("/Login")
public class Login extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public Login() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String username = request.getParameter("username").trim();
      String password = request.getParameter("password").trim();

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

      List<User> listUsers = null;
      if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
         listUsers = UtilDB.logInUser(username,password);
         if(listUsers.isEmpty()) {
        	out.println("<h1>There is no user with that login.</h1> <br>");
            out.println("<a href=/" + projectName + "/" + logIn + ">Log In</a> <br>");
         }else {
        	 UtilDB.getSession().setUser(listUsers.get(0));
        	 display(listUsers, out);
        	 System.out.print(UtilDB.getSession().getCurrentUser());
        	 out.println("<a href=/" + projectName + "/" + home + ">Home</a> <br>");
         }
      } else {
         //listEmployees = UtilDBWilliams.listUsers();
         out.println("<h1>There must be a username and password entered.</h1> <br>");
         out.println("<a href=/" + projectName + "/" + logIn + ">Log In</a> <br>");
      }
      
      out.println("</ul>");
      //out.println("<a href=/" + projectName + "/" + newMessage + ">Create Message</a> <br>");
      //out.println("<a href=/" + projectName + "/" + searchMessage + ">View Message</a> <br>");
      out.println("</body></html>");
   }

   void display(List<User> listUsers, PrintWriter out) {
      for (User employee : listUsers) {
         System.out.println("[DBG] " + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getEmail());

         out.println("<li>" + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getEmail() + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
