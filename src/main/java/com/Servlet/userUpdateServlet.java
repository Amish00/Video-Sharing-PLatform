package com.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

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
@WebServlet("/update")
@MultipartConfig(maxFileSize = 16177215) // 15MB
public class userUpdateServlet extends HttpServlet {
	
	@Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("wel") == null) {
            // Session expired or user not logged in
            request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }
		request.getRequestDispatcher("./Admin/userUpdate.jsp").forward(request, response);
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("wel") == null) {
            // Session expired or user not logged in
            request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }
        String uidParam = request.getParameter("uid");

        if (uidParam == null || uidParam.isEmpty()) {
            request.setAttribute("remove", "User ID is missing.");
            request.getRequestDispatcher("./Admin/userUpdate.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(uidParam);
        } catch (NumberFormatException e) {
            request.setAttribute("remove", "Invalid User ID.");
            request.getRequestDispatcher("./Admin/userUpdate.jsp").forward(request, response);
            return;
        }

        // Retrieve form parameters
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String phoneNumber = request.getParameter("phonenumber");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String bio = request.getParameter("bio");
        String role = request.getParameter("role");

        // Handle profile photo upload
        Part filePart = request.getPart("profilepic");
        byte[] profilePhoto = null;
        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream()) {
                profilePhoto = inputStream.readAllBytes();
            }
        } else {
            // No new photo uploaded, fetch existing photo from DB
            UserController userController = new UserControllerImplements();
            User existingUser = userController.getUserById(id);
            if (existingUser != null) {
                profilePhoto = existingUser.getProfilePhoto(); // Retain old photo if none uploaded
            }
        }

        // Create User object and set properties
        User user = new User();
        user.setUid(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setGender(gender);
        user.setEmail(email);
        user.setUsername(username);
        user.setBio(bio);
        user.setRole(role);
        user.setProfilePhoto(profilePhoto);

        // Update user
        UserController userController = new UserControllerImplements();
        boolean isUpdated = userController.userUpdate(user);

        if (isUpdated) {
            request.setAttribute("edit", "User updated successfully!");
        } else {
            request.setAttribute("remove", "Failed to update user.");
        }

        // Forward back to the UserTable or another page
        request.setAttribute("AllData", userController.getAllUserData());
        request.setAttribute("userList", userController.getAllUserData());
        request.getRequestDispatcher("./Admin/UserTable.jsp").forward(request, response);
    }
}
