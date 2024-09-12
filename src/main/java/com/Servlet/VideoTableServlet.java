package com.Servlet;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;
import com.Model.Video;

@WebServlet("/videotable")
public class VideoTableServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("wel");
        
      
        if (userEmail == null) { 
            request.setAttribute("sessionError", "You must be logged in to access the video table page.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; // Ensure no further processing occurs
        }

        UserController uc = new UserControllerImplements();
        List<Video> vList = uc.getAllVideoData();
        
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

        request.setAttribute("AllVideoData", vList); // Sends list of videos to VideoTable.jsp
        request.getRequestDispatcher("./Admin/VideoTable.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Handle POST by delegating to doGet
    }
}
