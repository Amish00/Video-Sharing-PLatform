package com.Servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;


@WebServlet("/signup")
public class signupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = "user";
        
        // Email and Password validation
        if (!isValidEmail(email)) {
            request.setAttribute("errnotify", "Invalid email format");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        if (!isValidPassword(password)) {
            request.setAttribute("errnotify", "Password must be at least 8 characters long and include a mix of letters and numbers.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Password Hashing
        String hashPwd = DigestUtils.shaHex(password.getBytes());
        
        User u = new User();
        u.setEmail(email);
        u.setUsername(username);
        u.setPassword(hashPwd);
        u.setRole(role);
        
        UserController uc = new UserControllerImplements();
        if (uc.userSignup(u)) {
            request.setAttribute("notify", "Successful Signup! Please Login!!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("errnotify", "Unsuccessful Signup! Please try again!!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*");
    }
}
