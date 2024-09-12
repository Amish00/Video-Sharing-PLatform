package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;

@WebServlet("/uploadFormServlet")
public class uploadFormServlet extends HttpServlet {

    private UserController userController = new UserControllerImplements();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get session without creating a new one

        // Check if session exists and user is logged in
        if (session == null || session.getAttribute("wel") == null) {
            request.setAttribute("error", "Session expired. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Retrieve logged-in user email
        String email = (String) session.getAttribute("wel");

        // Fetch logged-in user details
        User loggedInUser = userController.getUserByEmail(email);
        if (loggedInUser != null && loggedInUser.getProfilePhoto() != null) {
            String base64ProfilePhoto = Base64.getEncoder().encodeToString(loggedInUser.getProfilePhoto());
            loggedInUser.setBase64ProfilePhoto(base64ProfilePhoto);
            request.setAttribute("profileImage", base64ProfilePhoto);
            request.setAttribute("hasProfilePhoto", true);
        } else {
            request.setAttribute("hasProfilePhoto", false);
        }

        request.setAttribute("loggedInUser", loggedInUser); // Add logged-in user to request

        // Retrieve uploaded video ID from session
        Integer videoId = (Integer) session.getAttribute("uploadedVideoId");

        if (videoId == null) {
            response.getWriter().println("No video found.");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/videodb", "root", "System@991");

            // Fetch video details from the database
            String sql = "SELECT * FROM video WHERE vid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, videoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("videoTitle", rs.getString("title"));
                request.setAttribute("uploadDate", rs.getDate("uploadDate"));
                request.setAttribute("videoname", rs.getString("videoname"));
                request.setAttribute("videoLocation", rs.getString("videolocation"));
            }

            // Forward to the uploadForm.jsp
            request.getRequestDispatcher("uploadForm.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
