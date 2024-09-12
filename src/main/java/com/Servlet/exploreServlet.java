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
import com.Model.Video;

import java.util.Base64;
import java.util.List;

@WebServlet("/explore")
public class exploreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserController uc = new UserControllerImplements();
        
        
        
        String searchQuery = request.getParameter("search");
        String sortBy = request.getParameter("sortBy"); 
        List<Video> vList;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            // Fetch videos by search query
            vList = uc.searchVideosByTitle(searchQuery);
        } else {
            String category = request.getParameter("category");
            if (category != null && !category.isEmpty() && !category.equals("all")) {
                vList = uc.getVideosByCategory(category);
                request.setAttribute("selectedCategory", category); 
            } else {
                vList = uc.getAllApprovedVideoData();
                request.setAttribute("selectedCategory", "all");
            }
        }
        
        if (sortBy != null && sortBy.equals("views")) {
            vList = uc.sortVideosByViews(vList);  // Sort by views
        }

        // Fetch logged-in user profile data
        User loggedInUser = uc.getLoggedInUser(session);
        
        if (loggedInUser == null) {
        	request.setAttribute("error", "Session expired. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Pass the user profile and videos to the JSP
        request.setAttribute("AllVideoData", vList);
        request.setAttribute("loggedInUser", loggedInUser);
        request.setAttribute("searchQuery", searchQuery);
        request.setAttribute("sortBy", sortBy); 
        request.getRequestDispatcher("explore.jsp").forward(request, response);
    }
}


