package com.Servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;

@WebServlet("/uploadServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 10, // 10MB
    maxFileSize = 1024 * 1024 * 1024,      // 1GB
    maxRequestSize = 1024 * 1024 * 1024    // 1GB
)
public class uploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "C:\\Users\\nepac\\Documents\\workspace-spring-tool-suite-4\\Adv_Java_project\\src\\main\\webapp\\assets\\vid";
    private UserController userController = new UserControllerImplements();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Get session without creating a new one

        if (session == null || session.getAttribute("wel") == null) {
            // If session or user is not logged in, redirect to login page
            request.setAttribute("error", "Session expired. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String userEmail = (String) session.getAttribute("wel");

        // Retrieve logged-in user information
        User loggedInUser = userController.getUserByEmail(userEmail);
        if (loggedInUser == null) {
            response.getWriter().println("User not found.");
            return;
        }
        if (loggedInUser != null && loggedInUser.getProfilePhoto() != null) {
            String base64ProfilePhoto = Base64.getEncoder().encodeToString(loggedInUser.getProfilePhoto());
            loggedInUser.setBase64ProfilePhoto(base64ProfilePhoto);
        }

        // Set the user object in the request to pass to JSP
        request.setAttribute("loggedInUser", loggedInUser);

        // Forward to upload.jsp
        request.getRequestDispatcher("upload.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Get session without creating a new one

        if (session == null || session.getAttribute("wel") == null) {
            // If session or user is not logged in, redirect to login page
            request.setAttribute("error", "Session expired. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String userEmail = (String) session.getAttribute("wel");

        // Retrieve logged-in user information
        User loggedInUser = userController.getUserByEmail(userEmail);
        if (loggedInUser == null) {
            response.getWriter().println("User not found.");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Database connection setup
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/videodb", "root", "System@991");

            // File uploading logic
            String uploadFilePath = UPLOAD_DIR;
            File uploadDir = new File(uploadFilePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Create directories if they don't exist
            }

            Part filePart = request.getPart("file");
            String fileName = extractFileName(filePart);
            if (fileName != null && !fileName.isEmpty()) {
                String filePath = uploadFilePath + File.separator + fileName;
                filePart.write(filePath);

                // Insert video details into the database
                String sql = "INSERT INTO video (videoname, uploaderid, videolocation, uploadDate, isApproved) VALUES (?, ?, ?, NOW(), false)";
                stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, fileName);
                stmt.setInt(2, loggedInUser.getUid()); // Use logged-in user's ID
                stmt.setString(3, filePath);

                stmt.executeUpdate();
                
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                int videoId = -1;
                if (generatedKeys.next()) {
                    videoId = generatedKeys.getInt(1); // Get the generated video ID
                }

                // Store videoId in session for further processing
                session.setAttribute("uploadedVideoId", videoId);

                // Redirect to the uploadFormServlet to display video details
                response.sendRedirect("uploadFormServlet");

            } else {
                request.setAttribute("error", "No file selected or upload failed.");
                request.getRequestDispatcher("upload.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "File upload failed due to an internal error.");
            request.getRequestDispatcher("upload.jsp").forward(request, response);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to extract file name from the part header
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
    }
}
