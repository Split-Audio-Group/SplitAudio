import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.User;
import util.Info;
import util.UtilDB;

@WebServlet("/DatabaseSearch")
public class DatabaseSearch extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public DatabaseSearch() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

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
      if (keyword != null && !keyword.isEmpty()) {
    	  listUsers = UtilDB.listUsers(keyword);
      } else {
    	  listUsers = UtilDB.listUsers();
      }
      display(listUsers, out);
      out.println("</ul>");
      out.println("</body></html>");
   }

   void display(List<User> listUsers, PrintWriter out) {
      for (User user : listUsers) {
         System.out.println("[DBG] " + user.getId() + ", " //
               + user.getUserName() + ", " //
               + user.getDisplayName() + ", "
               + user.getEmail() + ", "	
               + user.getPassword());

         out.println("<li>" + user.getId() + ", " //
                 + user.getUserName() + ", " //
                 + user.getDisplayName() + ", "
                 + user.getEmail() + ", "	
                 + user.getPassword() +  "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
