package com.Servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller.UserController;
import com.Controller.UserControllerImplements;
import com.Model.Video;
import com.google.gson.Gson;

@WebServlet("/videodata")
public class VideoDataServlet extends HttpServlet {

    private UserController userController = new UserControllerImplements();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all video data from the database
        List<Video> videoList = userController.getVideoData();

        // Convert the video list to JSON
        String videoJson = gson.toJson(videoList);

        // Set response headers for JSON output
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write the JSON response
        response.getWriter().write(videoJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
