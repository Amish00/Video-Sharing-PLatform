package com.Servlet;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;

@WebServlet("/edit")
public class editServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false); 
        String userEmail = (String) session.getAttribute("wel");
        if (session == null || userEmail == null) {
            // If session is null or the user is not logged in, redirect to login page
            request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }

        // Get the logged-in user (if needed for additional logic)
        UserController uc = new UserControllerImplements();
       
        
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

        // Proceed with existing logic to fetch and update the user
        String uidParam = request.getParameter("uid"); // Ensure you're using the correct parameter name

        if (uidParam == null || uidParam.isEmpty()) {
            request.setAttribute("error", "User ID is missing.");
            request.getRequestDispatcher(".Admin/userUpdate.jsp").forward(request, response);
            return; // Stop further execution
        }

        // Parse the ID
        int id = Integer.parseInt(uidParam);
        
        User u = uc.getUserById(id); // Fetch user by ID
        
        if (u == null) {
            request.setAttribute("error", "User not found.");
            request.getRequestDispatcher("./Admin/userUpdate.jsp").forward(request, response);
            return; // Stop further execution if user is null
        }

        // Pass the user object to the JSP
        request.setAttribute("user", u);
        request.getRequestDispatcher("./Admin/userUpdate.jsp").forward(request, response);
    }
}
