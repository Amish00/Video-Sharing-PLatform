package com.Servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;

@WebServlet("/resetPassword")
public class resetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        String email = (String) session.getAttribute("uemail"); // Retrieve the email from session

        System.out.println("Email from session: " + email);  // Debugging
        System.out.println("New Password: " + newPassword);  // Debugging

        RequestDispatcher dispatcher = null;

        // Password validation criteria
        if (newPassword != null && confPassword != null && newPassword.equals(confPassword) && email != null) {
            if (isValidPassword(newPassword)) {
                UserController uc = new UserControllerImplements();
                boolean isUpdated = uc.updatePassword(email, newPassword); // Update password

                if (isUpdated) {
                    request.setAttribute("notify", "Psaaword Reset Successfullu");
                    dispatcher = request.getRequestDispatcher("login.jsp");
                } else {
                    request.setAttribute("error", "Password Reset Failed, Try Again");
                    dispatcher = request.getRequestDispatcher("resetPassword.jsp");
                }
            } else {
                request.setAttribute("error", "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character.");
                dispatcher = request.getRequestDispatcher("resetPassword.jsp");
            }
        } else {
            request.setAttribute("error", "Passwords do not match or session has expired. Try again.");
            dispatcher = request.getRequestDispatcher("resetPassword.jsp");
        }

        dispatcher.forward(request, response);
    }

    // Method to validate the password based on given criteria
    private boolean isValidPassword(String password) {
        // Password must be at least 8 characters long, contain at least one uppercase letter,
        // one lowercase letter, one digit, and one special character.
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.matches(passwordPattern, password);
    }
}
