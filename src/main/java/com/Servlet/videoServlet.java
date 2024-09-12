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
import java.sql.Timestamp;

@WebServlet("/video")
public class videoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get video ID from the request
        String videoIdParam = request.getParameter("vid");
        if (videoIdParam == null || videoIdParam.isEmpty()) {
            response.sendRedirect("explore.jsp");
            return;
        }

        int videoId = Integer.parseInt(videoIdParam);
        
        // Fetch the currently logged-in user
        HttpSession session = request.getSession(false);  // Use false to avoid creating a new session
        UserController userController = new UserControllerImplements();

        // Retrieve the logged-in user
        User loggedInUser = userController.getLoggedInUser(session);

        if (loggedInUser == null) {
            // If no user is logged in, redirect to login page with error message
            request.setAttribute("error", "Session expired. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Fetch video details using the UserController
        Video video = userController.getVideoById(videoId);  // Implement this method in the controller

        // Check if the video exists
        if (video == null) {
            response.sendRedirect("explore.jsp");
            return;
        }

        // Increment video views and log the watch history
        userController.incrementVideoViews(videoId);
        Timestamp watchDate = new Timestamp(System.currentTimeMillis());
        userController.addHistory(loggedInUser.getUid(), videoId, watchDate);

        // Set video object in the request
        request.setAttribute("VideoData", video);

        // Set logged-in user info (e.g., profile photo, name) in the request, if needed on video.jsp
        request.setAttribute("loggedInUser", loggedInUser);

        // Forward to video.jsp
        request.getRequestDispatcher("video.jsp").forward(request, response);
    }
}
