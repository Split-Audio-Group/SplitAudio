import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.User;
import util.*;

@WebServlet("/Signup")
public class Signup extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public Signup() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		   throws ServletException, IOException {
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
      out.println("<h1>Account sucessfully created</h1> <br>");
      out.println("<a href=/" + projectName + "/" + logIn + ">Log In</a> <br>");
      
      /*
      	List<User> list_user =null;
        list_user = UtilDB.logInUser(userName, password);
    	UtilDB.getSession().setUser(list_user.get(0));
        System.out.print(UtilDB.getSession().getCurrentUser());
     	out.println("<a href=/" + projectName + "/" + home + ">Home</a> <br>");
      */
      
   }
   


   protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		   throws ServletException, IOException {
      doGet(request, response);
   }
}
