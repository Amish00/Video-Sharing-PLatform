package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;

@WebServlet("/deleteUnapprovedVideo")
public class DeleteUnapprovedVideoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Get the video ID from the form
	    String videoIdParam = request.getParameter("videoId");
	    
	    // Log the received videoId
	    System.out.println("Received videoId: " + videoIdParam);

	    // Validate the videoId to ensure it's not null or empty
	    if (videoIdParam == null || videoIdParam.isEmpty()) {
	        request.setAttribute("error", "Invalid or missing video ID.");
	        request.getRequestDispatcher("Approvalpage.jsp").forward(request, response);
	        return;  // Exit early since the video ID is invalid
	    }

	    try {
	        // Parse the videoId into an integer
	        int videoId = Integer.parseInt(videoIdParam);

	        // Initialize the controller that handles video actions
	        UserController uc = new UserControllerImplements();

	        // Attempt to delete the video using the provided videoId
	        boolean isDeleted = uc.deleteVideo(videoId);

	        // Redirect or forward based on the deletion result
	        if (isDeleted) {
	            request.setAttribute("notify", "The unapproved video was successfully deleted.");
	            response.sendRedirect("approval");  // Redirect back to the approval page
	        } else {
	            request.setAttribute("error", "Failed to delete the unapproved video.");
	            request.getRequestDispatcher("Approvalpage.jsp").forward(request, response);
	        }

	    } catch (NumberFormatException e) {
	        // Handle the case where videoId is not a valid integer
	        request.setAttribute("error", "Invalid video ID format.");
	        request.getRequestDispatcher("Approvalpage.jsp").forward(request, response);
	    }
	}

}
