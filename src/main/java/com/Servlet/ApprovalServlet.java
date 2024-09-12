package com.Servlet;

import com.Model.Video;
import com.Model.User;
import com.Controller.UserController;
import com.Controller.UserControllerImplements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@WebServlet("/approval")
public class ApprovalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        User loggedInUser = getLoggedInUser(session);

        if (loggedInUser == null || loggedInUser.getEmail() == null) {
            request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }

        // If the user is logged in, proceed with fetching unapproved videos
        UserController uc = new UserControllerImplements();
        List<Video> unapprovedVideos = uc.getUnapprovedVideos();

        // Pass the logged-in user and unapproved videos to the JSP
        request.setAttribute("loggedInUser", loggedInUser);
        request.setAttribute("unapprovedVideos", unapprovedVideos);
        request.getRequestDispatcher("/Admin/ApprovalPage.jsp").forward(request, response);
    }

    // Helper method to get the logged-in user
    private User getLoggedInUser(HttpSession session) {
        if (session == null) {
            return new User(); // Return default user if no session exists
        }

        String userEmail = (String) session.getAttribute("wel");
        if (userEmail == null) {
            return new User(); // Return default user if no email is found in session
        }

        UserController uc = new UserControllerImplements();
        User user = uc.getUserByEmail(userEmail);

        // Convert profile photo to Base64 if it exists
        if (user != null && user.getProfilePhoto() != null) {
            String base64ProfilePhoto = Base64.getEncoder().encodeToString(user.getProfilePhoto());
            user.setBase64ProfilePhoto(base64ProfilePhoto);
        }

        return user != null ? user : new User(); // If user is null, return a default User object
    }
}
