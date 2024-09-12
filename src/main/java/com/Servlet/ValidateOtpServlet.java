package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ValidateOtp")
public class ValidateOtpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int value = Integer.parseInt(request.getParameter("otp"));
        HttpSession session = request.getSession();
        int otp = (int) session.getAttribute("otp");

        RequestDispatcher dispatcher = null;

        if (value == otp) {
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "Success");
            dispatcher = request.getRequestDispatcher("resetPassword.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Incorrect OTP code");
            dispatcher = request.getRequestDispatcher("OTP.jsp");
            dispatcher.forward(request, response);
        }
    }
}
