
package com.Servlet;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteVideo")
public class DeleteVideoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the video ID from the form
        int videoId = Integer.parseInt(request.getParameter("videoId"));

        // Initialize UserController
        UserController uc = new UserControllerImplements();

        // Call delete method
        boolean isDeleted = uc.deleteVideo(videoId);

        // Redirect back to the myvideos page after deletion
        if (isDeleted) {
        	request.setAttribute("notify", "The video is deleted.");
            response.sendRedirect("myvideos");
        } else {
            request.setAttribute("remove", "Failed to delete the video.");
            request.getRequestDispatcher("/myVideo.jsp").forward(request, response);
        }
    }
}
