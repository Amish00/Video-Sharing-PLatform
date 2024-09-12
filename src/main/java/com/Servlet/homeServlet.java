package com.Servlet;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;
import com.Model.Video;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@WebServlet("/home")
public class homeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("wel");

        UserController uc = new UserControllerImplements();
        
        if (userEmail == null) {
        	request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }

        // Get recommended videos
        List<Video> recommendedVideos = uc.getRecommendedVideos();

        // Convert video thumbnails to Base64
        for (Video v : recommendedVideos) {
            if (v.getThumbnail() != null) {
                String base64Thumbnail = Base64.getEncoder().encodeToString(v.getThumbnail());
                v.setBase64Thumbnail(base64Thumbnail);
            }
        }

        // If user is logged in, fetch user profile details
        if (userEmail != null) {
            User user = uc.getUserByEmail(userEmail);

            // Convert profile photo to Base64 if it exists
            if (user.getProfilePhoto() != null) {
                String base64ProfilePhoto = Base64.getEncoder().encodeToString(user.getProfilePhoto());
                user.setBase64ProfilePhoto(base64ProfilePhoto);
            }

            // Add user details to the request
            request.setAttribute("loggedInUser", user);
        }

        // Add recommended videos to the request
        request.setAttribute("RecommendedVideos", recommendedVideos);

        // Forward to the home.jsp
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
