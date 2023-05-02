import java.io.IOException;
import java.util.regex.Pattern;

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
        // Retrieve user input
        String userName = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String email = request.getParameter("email").trim();
        String displayName = request.getParameter("displayname").trim();

        // Validate user input
        if (!isValidUserName(userName)) {
            response.sendRedirect(request.getContextPath() + "/Signup.html?error=invalidUserName");
            return;
        }

        if (!isValidPassword(password)) {
            response.sendRedirect(request.getContextPath() + "/Signup.html?error=invalidPassword");
            return;
        }

        if (!isValidEmail(email)) {
            response.sendRedirect(request.getContextPath() + "/Signup.html?error=invalidEmail");
            return;
        }

        // Save user to database
        UtilDB.createUser(userName, displayName, email, password);

        // Redirect to login page with success message
        response.sendRedirect(request.getContextPath() + "/LogIn.html?success=goodSignUp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // Check if the provided user name is valid
    private boolean isValidUserName(String userName) {
        boolean valid = false;
        List<User> resultList= UtilDB.listUsers(userName);
        if (resultList.isEmpty() && !userName.isEmpty() && Pattern.matches("^[a-zA-Z0-9._-]{3,30}$", userName)) {
            valid = true;
        }
            
        return valid;
    }

    // Check if the provided password is valid
    private boolean isValidPassword(String password) {
        return password.length() >= 5 && password.length() <= 30;
    }

    // Check if the provided email is valid
    private boolean isValidEmail(String email) {
        boolean valid = false;
        List<User> resultList= UtilDB.listUsersEmail(email);
        if (resultList.isEmpty() && !email.isEmpty() && Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email) && email.length() <= 30) {
            valid = true;
        }
            
        return valid;
    }
}
