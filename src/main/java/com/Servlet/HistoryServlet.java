package com.Servlet;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.History;
import com.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Base64;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the logged-in user
        HttpSession session = request.getSession(false); // false so it doesn't create a new session if not exists
        User loggedInUser = getLoggedInUser(session);

        if (loggedInUser == null || loggedInUser.getEmail() == null) {
            request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch history data for all users
        UserController uc = new UserControllerImplements();
        List<History> historyList = uc.getAllHistoryData();

        // Set attributes and forward to JSP
        request.setAttribute("loggedInUser", loggedInUser); // Logged-in user data for displaying in JSP
        request.setAttribute("historyData", historyList); // Sends list of history to History.jsp
        request.getRequestDispatcher("./Admin/HistoryPage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Example code to handle POST requests, such as adding new history
        int userId = Integer.parseInt(request.getParameter("userId"));
        int videoId = Integer.parseInt(request.getParameter("videoId"));
        Timestamp watchDate = Timestamp.valueOf(request.getParameter("watchDate"));

        UserController uc = new UserControllerImplements();
        uc.addHistory(userId, videoId, watchDate);

        response.sendRedirect("history"); // Redirect to GET method to view updated history
    }

    // Helper method to get the logged-in user
    private User getLoggedInUser(HttpSession session) {
        if (session == null) {
            return new User(); // Return default user if no session exists
        }

        String userEmail = (String) session.getAttribute("wel");
        if (userEmail == null) {
            return new User(); // Return default user if no email is found in session
        }

        UserController uc = new UserControllerImplements();
        User user = uc.getUserByEmail(userEmail);

        // Convert profile photo to Base64 if it exists
        if (user != null && user.getProfilePhoto() != null) {
            String base64ProfilePhoto = Base64.getEncoder().encodeToString(user.getProfilePhoto());
            user.setBase64ProfilePhoto(base64ProfilePhoto);
        }

        return user != null ? user : new User(); // If user is null, return a default User object
    }
}
