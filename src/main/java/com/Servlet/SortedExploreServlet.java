package com.Servlet;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/sortedExplore")
public class SortedExploreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserController uc = new UserControllerImplements();
        
        // Retrieve search query and sort option
        String searchQuery = request.getParameter("search");
        String sortOption = request.getParameter("sort");
        List<Video> vList;

        // Sorting logic based on user's selection
        if (searchQuery != null && !searchQuery.isEmpty()) {
            vList = uc.searchVideosByTitle(searchQuery);  // Search videos by title
        } else {
            vList = uc.getAllApprovedVideoData();  // Retrieve all approved videos
        }

        // Sort videos based on views if the 'views' option is selected
        if ("views".equalsIgnoreCase(sortOption)) {
            vList.sort((v1, v2) -> Integer.compare(v2.getViews(), v1.getViews())); // Sort by views (descending)
        }

        // Fetch logged-in user profile data
        User loggedInUser = uc.getLoggedInUser(session);
        if (loggedInUser == null) {
            request.setAttribute("error", "Session expired. Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Pass data to JSP
        request.setAttribute("AllVideoData", vList);
        request.setAttribute("loggedInUser", loggedInUser);
        request.setAttribute("searchQuery", searchQuery);
        request.setAttribute("sortOption", sortOption);
        request.getRequestDispatcher("sortedExplore.jsp").forward(request, response);
    }
}
