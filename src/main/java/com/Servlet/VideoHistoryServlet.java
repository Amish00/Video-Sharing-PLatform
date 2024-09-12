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
import java.util.List;

@WebServlet("/videoHistory")
public class VideoHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("wel");

        if (userEmail != null) {
            UserController uc = new UserControllerImplements();
            int userId = uc.getUserIdByEmail(userEmail);

            // Fetch user's video history
            List<Video> videoHistoryList = uc.getUserVideoHistory(userId);

            // Fetch logged-in user profile data
            User loggedInUser = uc.getLoggedInUser(session);

            // Set the video history and user profile data in request
            request.setAttribute("videoHistoryList", videoHistoryList);
            request.setAttribute("loggedInUser", loggedInUser);

            request.getRequestDispatcher("/videoHistory.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
