package com.Servlet;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;

@WebServlet("/usertable")
public class tableServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve the logged-in user's email from the session
        String userEmail = (String) session.getAttribute("wel");
        if (userEmail == null) {
            request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }

        // Set up UserController
        UserController userController = new UserControllerImplements();

        // Check if user is logged in
        if (userEmail != null) {
            User user = userController.getUserByEmail(userEmail);

            // If the user has a profile photo, convert it to Base64
            if (user != null && user.getProfilePhoto() != null) {
                String base64ProfilePhoto = Base64.getEncoder().encodeToString(user.getProfilePhoto());
                user.setBase64ProfilePhoto(base64ProfilePhoto);
            }

            // Set the user as a request attribute
            request.setAttribute("loggedInUser", user);
        }

        // Get the list of all users
        List<User> uList = userController.getAllUserData();
        request.setAttribute("AllData", uList);

        // Retrieve the message from the session and pass it to the request
        String message = (String) session.getAttribute("remove");
        if (message != null) {
            request.setAttribute("remove", message);
            session.removeAttribute("remove"); // Clear the session attribute after displaying the message
        }

        // Forward to the UserTable.jsp
        request.getRequestDispatcher("./Admin/UserTable.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Handle POST by delegating to doGet
    }
}
