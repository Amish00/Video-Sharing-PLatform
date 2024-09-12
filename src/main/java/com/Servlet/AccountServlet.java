package com.Servlet;

import java.io.IOException;
import java.io.InputStream;
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

@WebServlet("/account")
@MultipartConfig(maxFileSize = 16177215)  // Max upload size of 15MB for the profile picture
public class AccountServlet extends HttpServlet {
    private UserController userController = new UserControllerImplements();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get session without creating a new one

        if (session == null || session.getAttribute("wel") == null) {
            // If session or "wel" attribute is null, redirect to login
            request.setAttribute("error", "Session expired. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Retrieve logged-in user's email from session
        String email = (String) session.getAttribute("wel");

        // Fetch user details
        User user = userController.getUserByEmail(email);
        if (user != null) {
            // If profile photo exists, convert to Base64
            if (user.getProfilePhoto() != null && user.getProfilePhoto().length > 0) {
                String base64Image = Base64.getEncoder().encodeToString(user.getProfilePhoto());
                user.setBase64ProfilePhoto(base64Image);
                request.setAttribute("profileImage", base64Image);
                request.setAttribute("hasProfilePhoto", true);
            } else {
                request.setAttribute("hasProfilePhoto", false);
            }

            // Set user details in request
            request.setAttribute("loggedInUser", user); // Added to make user available in the JSP
            request.setAttribute("user", user);
        }

        // Forward to the account page
        request.getRequestDispatcher("account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get session without creating a new one

        if (session == null || session.getAttribute("wel") == null) {
            // If session or "wel" attribute is null, redirect to login
            request.setAttribute("error", "Session expired. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String email = (String) session.getAttribute("wel");

        // Fetch the user and update details
        User user = userController.getUserByEmail(email);

        if (user != null) {
            // Update user information
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setPhoneNumber(request.getParameter("phoneNumber"));
            user.setGender(request.getParameter("gender"));
            user.setBio(request.getParameter("bio"));

            // Handle profile photo update
            Part filePart = request.getPart("profilePhoto");
            if (filePart != null && filePart.getSize() > 0) {
                InputStream inputStream = filePart.getInputStream();
                byte[] profilePhoto = inputStream.readAllBytes();
                user.setProfilePhoto(profilePhoto);
            }

            // Update user account
            boolean success = userController.updateUserAccount(user);
            if (success) {
                request.setAttribute("notify", "Account updated successfully!");
            } else {
                request.setAttribute("error", "Failed to update account.");
            }
        }

        // Refresh account info after update
        doGet(request, response);
    }
}
