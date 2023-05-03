import java.io.IOException;
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
	// Serial version UID for serialization/deserialization
	private static final long serialVersionUID = 1L;

	// Constructor
	public Login() {
		super();
	}

	// Handle GET request
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the username and password parameters from the request
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		
		// Check if the username and password are valid and exist in the database
		List<User> listUsers = null;
		if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
			listUsers = UtilDB.logInUser(username, password);
			if(listUsers.isEmpty()) { // User not found
				response.sendRedirect(request.getContextPath() + "/LogIn.html?success=noUser");
			} else { // User found
				// Set the session user to the first user in the list (assumes only one user with that username)
				UtilDB.getSession().setUser(listUsers.get(0));
				UtilDB.getSession();
				// Redirect to the login page with a success message
				response.sendRedirect(request.getContextPath() + "/Home2.html?success=goodLogin");
			}
		} else { // Username or password not provided
			response.sendRedirect(request.getContextPath() + "/LogIn.html?success=badUserPass");
		}
	}

	// Handle POST request
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}