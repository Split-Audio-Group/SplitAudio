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
import util.SessionLog;
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
      
      List<User> listUsers = null;
      if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
         listUsers = UtilDB.logInUser(username,password);
         if(listUsers.isEmpty()) {
        	 response.sendRedirect(request.getContextPath() + "/LogIn.html?success=noUser");
         }else {
        	 UtilDB.getSession().setUser(listUsers.get(0));
        	 UtilDB.getSession();
        	 response.sendRedirect(request.getContextPath() + "/LogIn.html?success=goodUserPass");
         }
      } else {
    	  response.sendRedirect(request.getContextPath() + "/LogIn.html?success=badUserPass");
      }
   }



   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
