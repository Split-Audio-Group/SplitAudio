import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("Search")
public class Search extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Search() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      search(email, phone, response);
   }

   void search(String email, String user, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      String tableName = "USERS";

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnection.getDBConnection(getServletContext());
         connection = DBConnection.connection;

         if (email.isEmpty() && user.isEmpty()) {
            String selectSQL = "SELECT * FROM "+ tableName;
            preparedStatement = connection.prepareStatement(selectSQL);
         } else if (!email.isEmpty() && user.isEmpty()) {
            String selectSQL = "SELECT * FROM "+ tableName +" WHERE EMAIL LIKE ?";
            String setEmail = "%" + email + "%";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, setEmail);
         } else if (email.isEmpty() && !user.isEmpty()) {
        	 String selectSQL = "SELECT * FROM "+ tableName +" WHERE USERNAME LIKE ?";
             String setPhone = "%" + user + "%";
             preparedStatement = connection.prepareStatement(selectSQL);
             preparedStatement.setString(1, setPhone); 
         } else {
        	 String selectSQL = "SELECT * FROM "+ tableName +" WHERE (EMAIL LIKE ? AND PHONE LIKE ?)";
             String setEmail = "%" + email + "%";
             String setPhone = "%" + user + "%";
             preparedStatement = connection.prepareStatement(selectSQL);
             preparedStatement.setString(1, setEmail);
             preparedStatement.setString(2, setPhone);
         }
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String userName = rs.getString("USERNAME").trim();
            String printEmail = rs.getString("EMAIL").trim();
            String printPassword = rs.getString("PASSWORD").trim();

            if ((printEmail.isEmpty() && userName.isEmpty()) || (printEmail.contains(email) || userName.contains(user))) {
               out.println("ID: " + id + ", ");
               out.println("User: " + userName + ", ");
               out.println("Email: " + printEmail + ", ");
               out.println("Password: " + printPassword + "<br>");
            }
         }
         out.println("<a href=/webproject/DatabaseSearch.html>Search Data</a> <br>");
         out.println("</body></html>");
         rs.close();
         preparedStatement.close();
         connection.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         } catch (SQLException se2) {
         }
         try {
            if (connection != null)
               connection.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
