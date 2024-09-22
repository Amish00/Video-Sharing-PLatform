package com.Servlet;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {
    private UserController userController = new UserControllerImplements();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User loggedInUser = userController.getLoggedInUser(session);

        if (loggedInUser == null) {
            // Redirect to login if not logged in
            request.setAttribute("error", "Session expired. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Get parameters
        String commentText = request.getParameter("commentText");
        int videoId = Integer.parseInt(request.getParameter("vid"));

        // Add comment to the database
        boolean success = userController.addComment(loggedInUser.getUid(), videoId, commentText);

        if (success) {
            // Redirect back to video page after comment is added
            response.sendRedirect("video?vid=" + videoId);
        } else {
            request.setAttribute("error", "Failed to add comment. Please try again.");
            request.getRequestDispatcher("video.jsp").forward(request, response);
        }
    }
}
