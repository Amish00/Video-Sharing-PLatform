package com.Servlet;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.regex.Pattern;

@WebServlet("/addUser")
@MultipartConfig(maxFileSize = 16177215)
public class AddUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        User loggedInUser = getLoggedInUser(session);

        if (loggedInUser == null || loggedInUser.getEmail() == null) {
            request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }

        request.setAttribute("loggedInUser", loggedInUser); // Send logged-in user data to JSP
        request.getRequestDispatcher("./Admin/addrow.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        User loggedInUser = getLoggedInUser(session);

        if (loggedInUser == null || loggedInUser.getEmail() == null) {
            request.setAttribute("error", "Session expired. Please log in.");
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve form parameters
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String phoneNumber = request.getParameter("phonenumber");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String bio = request.getParameter("bio");
        String role = "user"; // Default role

        // Handle profile photo upload
        Part filePart = request.getPart("profilephoto");
        byte[] profilePhoto = null;
        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream()) {
                profilePhoto = inputStream.readAllBytes(); // Reading all bytes from InputStream
            }
        }

        // Validate email and password
        if (!isValidEmail(email)) {
            request.setAttribute("errnotify", "Invalid email format");
            request.getRequestDispatcher("./Admin/addrow.jsp").forward(request, response);
            return;
        }

        if (!isValidPassword(password)) {
            request.setAttribute("errnotify", "Password must be at least 8 characters long and include a mix of letters and numbers.");
            request.getRequestDispatcher("./Admin/addrow.jsp").forward(request, response);
            return;
        }

        // Hash the password before storing
        String hashedPassword = DigestUtils.shaHex(password);

        // Create User object and set properties
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setGender(gender);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setRole(role);
        user.setBio(bio);
        user.setProfilePhoto(profilePhoto);

        // Save user
        UserController userController = new UserControllerImplements();
        boolean isUserAdded = userController.addUser(user);

        if (isUserAdded) {
            request.setAttribute("Status", "User added successfully!");
        } else {
            request.setAttribute("errnotify", "Failed to add user.");
        }

        // Forward back to the form or another page
        response.sendRedirect("usertable");
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

    // Email and password validation methods
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*");
    }
}
