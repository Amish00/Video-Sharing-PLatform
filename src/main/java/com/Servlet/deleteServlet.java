package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;

@WebServlet("/delete")
public class deleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("Uid");

            if (idParam == null || idParam.isEmpty()) {
                request.setAttribute("remove", "Failed to delete data: ID is missing");
            } else {
                int id = Integer.parseInt(idParam);

                UserController uc = new UserControllerImplements();
                boolean isDeleted = uc.deleteUser(id);

                if (isDeleted) {
                    request.setAttribute("remove", "Data has been deleted");
                } else {
                    request.setAttribute("remove", "Failed to delete data: User not found");
                }

                request.setAttribute("AllData", uc.getAllUserData());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("remove", "Failed to delete data: invalid ID");
        }

        request.getRequestDispatcher("./Admin/UserTable.jsp").forward(request, response);
    }
}
