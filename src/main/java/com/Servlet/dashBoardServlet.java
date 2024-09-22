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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet("/dash")
public class dashBoardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the logged-in user
        HttpSession session = request.getSession(false); // false so it doesn't create a new session
        User loggedInUser = getLoggedInUser(session);

        if (loggedInUser == null || loggedInUser.getEmail() == null) {
            request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }

        // Create a list of data for bar chart and pie chart
        List<Integer> weeklyUploads = new ArrayList<>();
        List<Integer> pieChartData = new ArrayList<>();
     // Views chart
        List<String> videoNames = new ArrayList<>();
        List<Integer> videoViews = new ArrayList<>();
        
        // Variables for user statistics
        int totalUsers = 0;
        int totalApprovedVideos = 0;
        int totalPendingVideos = 0;

        // Fetch data from the database
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/videodb", "root", "System@991")) {
            // Bar Chart Data (Weekly Uploads)
            String query = "SELECT COUNT(*) as uploads FROM video WHERE uploadDate >= CURDATE() - INTERVAL 7 DAY GROUP BY DATE(uploadDate)";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                weeklyUploads.add(rs.getInt("uploads")); // Fetch uploads count
            }

            // Pie Chart Data (Likes, Dislikes, Views)
            query = "SELECT SUM(likes) as likes, SUM(dislikes) as dislikes FROM video";
            statement = conn.prepareStatement(query);
            rs = statement.executeQuery();
            if (rs.next()) {
                pieChartData.add(rs.getInt("likes"));
                pieChartData.add(rs.getInt("dislikes"));
            }
            
            

            // Fetch video names and view counts
            query = "SELECT title, SUM(views) as total_views FROM video WHERE isApproved = true GROUP BY title";
            statement = conn.prepareStatement(query); 
             rs = statement.executeQuery();
                while (rs.next()) {
                    videoNames.add(rs.getString("title")); // Get videoname
                    videoViews.add(rs.getInt("total_views")); // Get total views
                }
            

            
            // Total Users
            query = "SELECT COUNT(*) as total FROM user";
            statement = conn.prepareStatement(query);
            rs = statement.executeQuery();
            if (rs.next()) {
                totalUsers = rs.getInt("total");
            }

            // Total Approved Videos
            query = "SELECT COUNT(*) as total FROM video WHERE isApproved = true";
            statement = conn.prepareStatement(query);
            rs = statement.executeQuery();
            if (rs.next()) {
                totalApprovedVideos = rs.getInt("total");
            }

            // Total Pending Approval Videos
            query = "SELECT COUNT(*) as total FROM video WHERE isApproved = false";
            statement = conn.prepareStatement(query);
            rs = statement.executeQuery();
            if (rs.next()) {
                totalPendingVideos = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set attributes to pass data to JSP
        request.setAttribute("loggedInUser", loggedInUser);
        request.setAttribute("videoNames", videoNames);
        request.setAttribute("videoViews", videoViews);
        request.setAttribute("weeklyUploads", weeklyUploads); // Change to weeklyUploads
        request.setAttribute("pieChartData", pieChartData);
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("totalApprovedVideos", totalApprovedVideos);
        request.setAttribute("totalPendingVideos", totalPendingVideos);

        // Forward to JSP page
        request.getRequestDispatcher("./Admin/dashboard.jsp").forward(request, response);
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
