package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;

@WebServlet("/cancelUpload")
public class CancelUploadServlet extends HttpServlet {

    private UserController userController = new UserControllerImplements();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer videoId = Integer.parseInt(request.getParameter("videoId"));

        if (videoId != null) {
            boolean isDeleted = userController.deleteVideo(videoId);

            if (isDeleted) {
                // Remove the uploaded video ID from the session
                session.removeAttribute("uploadedVideoId");

                // Redirect to the upload page with a success message
                session.setAttribute("notify", "Video upload canceled and video deleted.");
                response.sendRedirect("uploadServlet"); // Redirect to uploadServlet
            } else {
                // Redirect to upload page with an error message
                session.setAttribute("error", "Failed to cancel and delete the video.");
                response.sendRedirect("uploadServlet"); // Redirect to uploadServlet
            }
        } else {
            // If no videoId is found in session, redirect back
            response.sendRedirect("uploadServlet"); // Redirect to uploadServlet
        }
    }
}

