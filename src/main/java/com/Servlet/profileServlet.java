package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.User;

import java.util.logging.Logger;

@WebServlet("/profile")
public class profileServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(profileServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("wel");

        logger.info("Session email attribute: " + email);

        if (email != null) {
            UserController uc = new UserControllerImplements();
            User user = uc.getUserByEmail(email);
            
            if (user != null) {
                logger.info("User data fetched successfully. Forwarding to home.jsp");
                request.setAttribute("user", user);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else {
                logger.warning("No user found for email: " + email + ". Redirecting to login.jsp");
                response.sendRedirect("login.jsp");
            }
        } else {
            logger.warning("Session email not found. Redirecting to login.jsp");
            response.sendRedirect("login.jsp");
        }
    }
}


