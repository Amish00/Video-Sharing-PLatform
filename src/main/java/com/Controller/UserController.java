package com.Controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.Model.Comment;
import com.Model.History;
import com.Model.User;
import com.Model.Video;

public interface UserController {
	
    boolean userSignup(User u); // Create (returns boolean to indicate success/failure)
    
    String userLogin(String em, String pw); // Read (returns role if login is successful)
    
    public boolean updatePassword(String email, String newPassword); // ForgetPassword reset
    
    List<User> getAllUserData(); // For retrieving data  //Read
    
    public List<Video> getAllVideoData(); // video table
    
    public List<Video> getUnapprovedVideos(); // list Video Unapproval
    
    public void approveVideo(int videoId); // Video Approval
    
    public boolean deleteUser(int id);// delete user
    
    void addHistory(int userId, int videoId, Timestamp watchDate);
    
    List<History> getAllHistoryData();
    
    public boolean addUser(User user); 
    
    boolean userUpdate(User u);
    
    public User getUserById(int id);
    
    // Main Page
    
    
    public List<Video> getAllApprovedVideoData();
    
    public List<Video> getRecommendedVideos();
    
    public boolean updateUserAccount(User user);
    
    public User getUserByEmail(String email);
    
    boolean deleteVideo(int id);
    
    public Video getVideoById(int videoId);
    
    public void incrementVideoViews(int videoId);
    
    public void incrementLikes(int videoId);
    
    public void incrementDislikes(int videoId);
    
    public List<Video> getUserVideos(int userId);
    
    Integer getUserIdByEmail(String email);
    
    public List<Video> getUserVideoHistory(int userId);
    
    public User getLoggedInUser(HttpSession session);
    
    public List<Video> getVideosByCategory(String category);
    
    public List<Video> searchVideosByTitle(String query);
    
    public List<Video> sortVideosByViews(List<Video> videoList);
    
    public List<Video> getVideoData();
    
    public List<Comment> getCommentsByVideoId(int videoId);
    
    public boolean addComment(int userId, int videoId, String commentText);
    
}

