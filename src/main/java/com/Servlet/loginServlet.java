package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;


@WebServlet("/login")
public class loginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String em = request.getParameter("email");
        String pwd = request.getParameter("password");
        
        // Email and Password validation
        if (!isValidEmail(em)) {
            request.setAttribute("error", "Invalid email format");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (!isValidPassword(pwd)) {
            request.setAttribute("error", "Password must be at least 8 characters long and include a mix of letters and numbers.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String hashpwd = DigestUtils.shaHex(pwd.getBytes());
        UserController uc = new UserControllerImplements();
        String role = uc.userLogin(em, hashpwd);
        
        if (role != null) {
            session.setAttribute("wel", em);
            session.setMaxInactiveInterval(60); // Session timeout

            if (role.equalsIgnoreCase("user"))  {
                request.getRequestDispatcher("home").forward(request, response);
            } else if (role.equalsIgnoreCase("admin")) {
            	response.sendRedirect("dash");
            } else {
                request.setAttribute("error", "Access denied. Invalid role.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Email or Password is incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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