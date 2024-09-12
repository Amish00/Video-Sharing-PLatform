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

@WebServlet("/myvideos")
public class myVideoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("wel");

        if (email == null) {
        	request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }

        UserController uc = new UserControllerImplements();
        Integer userId = uc.getUserIdByEmail(email);
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch user's videos
        List<Video> userVideos = uc.getUserVideos(userId);

        // Convert thumbnails to Base64
        for (Video v : userVideos) {
            if (v.getThumbnail() != null) {
                String base64Thumbnail = Base64.getEncoder().encodeToString(v.getThumbnail());
                v.setBase64Thumbnail(base64Thumbnail);
            }
        }

        // Fetch logged-in user profile data
        User loggedInUser = uc.getLoggedInUser(session);

        // Set the user videos and profile data in request
        request.setAttribute("UserVideos", userVideos);
        request.setAttribute("loggedInUser", loggedInUser);

        request.getRequestDispatcher("/myVideo.jsp").forward(request, response);
    }
}
