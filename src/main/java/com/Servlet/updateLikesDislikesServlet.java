package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;

@WebServlet("/updateLikesDislikes")
public class updateLikesDislikesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get video ID and action (like/dislike) from the form
        String videoIdParam = request.getParameter("vid");
        String action = request.getParameter("action");

        // Check if vid or action is missing
        if (videoIdParam == null || videoIdParam.isEmpty() || action == null || action.isEmpty()) {
            response.sendRedirect("video.jsp?error=invalid");
            return;
        }

        int videoId = Integer.parseInt(videoIdParam);
        UserController uc = new UserControllerImplements();

        // Update likes or dislikes based on the action
        if ("like".equals(action)) {
            uc.incrementLikes(videoId);
        } else if ("dislike".equals(action)) {
            uc.incrementDislikes(videoId);
        }

        // Redirect back to the video page after updating
        response.sendRedirect("video?vid=" + videoId);
    }
}
