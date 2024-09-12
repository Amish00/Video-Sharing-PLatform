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

@WebServlet("/editVideo")
public class editMyVideoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve the logged-in user using the existing method
        UserController userController = new UserControllerImplements();
        User loggedInUser = userController.getLoggedInUser(session);

        // Check if the user is logged in
        if (loggedInUser == null) {
        	request.setAttribute("error", "Session expired. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String videoId = request.getParameter("videoId");

        if (videoId == null || videoId.isEmpty()) {
            response.sendRedirect("myvideos.jsp");
            return;
        }

        // Fetch video details
        Video video = userController.getVideoById(Integer.parseInt(videoId));

        if (video == null) {
            response.sendRedirect("myvideos.jsp");
            return;
        }

        // Add video and user details to the request scope
        request.setAttribute("loggedInUser", loggedInUser);
        request.setAttribute("videoId", video.getVid());
        request.setAttribute("videoTitle", video.getTitle());
        request.setAttribute("videoDescription", video.getDescription());
        request.setAttribute("videoCategory", video.getCategory());
        request.setAttribute("videoBase64Thumbnail", video.getBase64Thumbnail());
        request.setAttribute("videoname", video.getVideoname());

        // Forward to editMyVideo.jsp
        request.getRequestDispatcher("editMyVideo.jsp").forward(request, response);
    }
}
