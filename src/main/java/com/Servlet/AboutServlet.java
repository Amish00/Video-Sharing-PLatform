package com.Servlet;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/about")
public class AboutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session object
        HttpSession session = request.getSession();
        
        // Retrieve the logged-in user's email from the session
        String userEmail = (String) session.getAttribute("wel");
        
        if (userEmail == null) {
        	request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }

        // Check if user is logged in
        if (userEmail != null) {
            UserController userController = new UserControllerImplements();
            User user = userController.getUserByEmail(userEmail);

            // If the user has a profile photo, convert it to Base64
            if (user != null && user.getProfilePhoto() != null) {
                String base64ProfilePhoto = Base64.getEncoder().encodeToString(user.getProfilePhoto());
                user.setBase64ProfilePhoto(base64ProfilePhoto);
            }

            // Set the user as a request attribute
            request.setAttribute("loggedInUser", user);
        }

        // Forward the request to the "about.jsp" page
        request.getRequestDispatcher("about.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
