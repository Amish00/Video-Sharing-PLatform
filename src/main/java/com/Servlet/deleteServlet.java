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

@WebServlet("/delete")
public class deleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            String idParam = request.getParameter("Uid");

            if (idParam == null || idParam.isEmpty()) {
                session.setAttribute("remove", "Failed to delete data: ID is missing");
            } else {
                int id = Integer.parseInt(idParam);

                UserController uc = new UserControllerImplements();
                boolean isDeleted = uc.deleteUser(id);

                if (isDeleted) {
                    session.setAttribute("remove", "Data has been deleted");
                } else {
                    session.setAttribute("remove", "Failed to delete data: User not found");
                }

                session.setAttribute("AllData", uc.getAllUserData());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            session.setAttribute("remove", "Failed to delete data: invalid ID");
        }

        // Redirect to user table to ensure session-based navbar data is shown
        response.sendRedirect("usertable");
    }
}
