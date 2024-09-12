package com.Servlet;

import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/update_videos")
@MultipartConfig(maxFileSize = 16177215) // 16MB for thumbnail upload
public class updateMyVideoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Retrieve form data
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String[] categories = request.getParameterValues("category");
        String videoId = request.getParameter("videoId"); // Assuming videoId is passed for update.

        // Build the category string if multiple categories are selected
        StringBuilder categoryList = new StringBuilder();
        if (categories != null) {
            for (String category : categories) {
                if (categoryList.length() > 0) {
                    categoryList.append(", ");
                }
                categoryList.append(category);
            }
        }

        // Handling thumbnail upload
        Part thumbnailPart = request.getPart("thumbnail");
        InputStream thumbnailStream = null;
        boolean isThumbnailProvided = (thumbnailPart != null && thumbnailPart.getSize() > 0);

        if (isThumbnailProvided) {
            thumbnailStream = thumbnailPart.getInputStream(); // Get the input stream of the uploaded file
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Database connection setup (ensure your database credentials and driver are correct)
            String dbURL = "jdbc:mysql://localhost:3306/videodb";
            String dbUser = "root";
            String dbPass = "System@991";

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql;
            // SQL update query for video details and thumbnail (thumbnail as BLOB)
            if (isThumbnailProvided) {
                // Update title, description, categories, and thumbnail if a new one is provided
                sql = "UPDATE video SET title=?, description=?, category=?, thumbnail=? WHERE vid=?";
            } else {
                // Update title, description, and categories without changing the thumbnail
                sql = "UPDATE video SET title=?, description=?, category=? WHERE vid=?";
            }

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, categoryList.toString());

            if (isThumbnailProvided) {
                stmt.setBlob(4, thumbnailStream); // Set the new thumbnail as BLOB
                stmt.setString(5, videoId); // Set the video ID for the update
            } else {
                stmt.setString(4, videoId); // Set the video ID for the update
            }

            // Execute the update statement
            int row = stmt.executeUpdate();

            if (row > 0) {
            	request.setAttribute("notify", "Video updated successfully!");
            	response.sendRedirect("myvideos");
            } else {
            	request.setAttribute("error", "error: Failed to update video");
                request.getRequestDispatcher("editMyVideo.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "An error occurred while updating the video.");
            request.getRequestDispatcher("editMyVideo.jsp").forward(request, response);
        } finally {
            // Close the resources
            if (stmt != null) {
                try { stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            if (thumbnailStream != null) {
                try { thumbnailStream.close(); } catch (IOException e) { e.printStackTrace(); }
            }
        }
    }
}
