package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;

@WebServlet("/deleteUnapprovedVideo")
public class DeleteUnapprovedVideoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session
        HttpSession session = request.getSession();

        // Get the video ID from the form
        String videoIdParam = request.getParameter("videoId");
        System.out.println("Received videoId: " + videoIdParam);

        // Validate the videoId
        if (videoIdParam == null || videoIdParam.isEmpty()) {
            session.setAttribute("error", "Invalid or missing video ID.");
            response.sendRedirect("approval");
            return;
        }

        try {
            // Parse the videoId into an integer
            int videoId = Integer.parseInt(videoIdParam);

            // Initialize the controller that handles video actions
            UserController uc = new UserControllerImplements();

            // Attempt to delete the video
            boolean isDeleted = uc.deleteVideo(videoId);

            if (isDeleted) {
                session.setAttribute("notify", "The video was successfully unapproved.");
            } else {
                session.setAttribute("error", "Failed to delete the unapproved video.");
            }

            // Redirect back to the approval page
            response.sendRedirect("approval");

        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid video ID format.");
            response.sendRedirect("approval");
        }
    }
}
