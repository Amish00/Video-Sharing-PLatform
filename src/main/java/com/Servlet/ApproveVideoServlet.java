package com.Servlet;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/approve_video")
public class ApproveVideoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String videoIdParam = request.getParameter("videoId");
        if (videoIdParam != null) {
            int videoId = Integer.parseInt(videoIdParam);
            UserController uc = new UserControllerImplements();
            uc.approveVideo(videoId);
        }
        response.sendRedirect("approval"); // Redirect to the approval page after update
    }
}

