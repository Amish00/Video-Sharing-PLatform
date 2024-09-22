package com.Controller;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.Model.Comment;
import com.Model.History;
import com.Model.User;
import com.Model.Video;



public class UserControllerImplements implements UserController {

    private Connection conn;

    public UserControllerImplements() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/videoDB", "root", "System@991");
            if (conn != null) {
                System.out.println("Connection established successfully.");
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean userSignup(User u) {
        String sql = "INSERT INTO user (Email, Username, Password) VALUES (?,?,?)";
        try {
            if (conn == null) {
                System.err.println("Connection is null in userSignup method.");
                return false;
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, u.getEmail());
            pstm.setString(2, u.getUsername());
            pstm.setString(3, u.getPassword());
            pstm.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String userLogin(String em, String pw) {
        String sql = "SELECT role FROM user WHERE email = ? AND password = ?";
        try {
            if (conn == null) {
                System.err.println("Connection is null in userLogin method.");
                return null;
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, em);
            pstm.setString(2, pw);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updatePassword(String email, String newPassword) {
        boolean isUpdated = false;

        try (PreparedStatement statement = conn.prepareStatement("UPDATE user SET password = ? WHERE email = ?")) {
            
            statement.setString(1, DigestUtils.shaHex(newPassword.getBytes())); // Hash the new password
            statement.setString(2, email);
            
            int rowsAffected = statement.executeUpdate();
            isUpdated = (rowsAffected > 0);
            
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }

        return isUpdated;
    }
    
    @Override
	public List<User> getAllUserData() {
		
		List<User> uList = new ArrayList<>();
		
		String sql = "Select * from user";
		try {
			
			PreparedStatement pstm = conn.prepareStatement(sql); 
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
			    User u = new User();
			    u.setUid(rs.getInt("uid"));
			    u.setFirstName(rs.getString("firstname"));
			    u.setLastName(rs.getString("lastname"));
			    u.setPhoneNumber(rs.getString("phoneNumber"));
			    u.setGender(rs.getString("gender"));
			    u.setEmail(rs.getString("email"));
			    u.setUsername(rs.getString("Username"));
			    u.setPassword(rs.getString("password"));
			    u.setBio(rs.getString("bio"));
			    u.setProfilePhoto(rs.getBytes("profilePhoto"));
			    u.setRole(rs.getString("role"));

			    // Set base64 version of the profile photo
			    u.setBase64ProfilePhoto(u.getProfilePhotoBase64());
			    
			    uList.add(u);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uList;
	}

    @Override
    public List<Video> getAllVideoData() {

        List<Video> vList = new ArrayList<>();
        String sql = "SELECT v.*, u.firstname, u.lastname FROM video v JOIN user u ON v.uploaderid = u.uid";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Video v = new Video();
                v.setVid(rs.getInt("vid"));
                v.setTitle(rs.getString("title"));
                v.setVideoname(rs.getString("videoname"));
                v.setUploaderName(rs.getString("firstname") + " " + rs.getString("lastname")); // Combine first and last names
                v.setUploaderid(rs.getInt("uploaderid"));
                v.setViews(rs.getInt("views"));
                v.setThumbnail(rs.getBytes("thumbnail"));
                v.setLikes(rs.getInt("likes"));
                v.setDislikes(rs.getInt("dislikes"));
                v.setDescription(rs.getString("description"));
                v.setVideoLocation(rs.getString("videoLocation"));
                v.setUploadDate(rs.getString("uploadDate"));
                v.setCategory(rs.getString("category"));
                v.setApproved(rs.getBoolean("isApproved"));

                vList.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vList;
    }
    
    @Override
    public List<Video> getUnapprovedVideos() {
        List<Video> videoList = new ArrayList<>();
        String sql = "SELECT * FROM video WHERE isApproved = false";
        try (PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                Video video = new Video();
                video.setVid(rs.getInt("vid"));
                video.setTitle(rs.getString("title"));
                video.setVideoname(rs.getString("videoname"));
                video.setUploaderid(rs.getInt("uploaderid"));
                video.setViews(rs.getInt("views"));
                video.setLikes(rs.getInt("likes"));
                video.setDislikes(rs.getInt("dislikes"));
                video.setDescription(rs.getString("description"));
                video.setVideoLocation(rs.getString("videoLocation"));
                video.setUploadDate(rs.getString("uploadDate"));
                video.setCategory(rs.getString("category"));
                video.setApproved(rs.getBoolean("isApproved"));

                videoList.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videoList;
    }

    public void approveVideo(int videoId) {
        String sql = "UPDATE video SET isApproved = true WHERE vid = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, videoId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean deleteUser(int id) {
		String sql = "DELETE FROM user WHERE uid=?";
		try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
    }
    
    @Override
    public void addHistory(int userId, int videoId, Timestamp watchDate) {
        String sql = "INSERT INTO history (user_id, video_id, watch_date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, videoId);
            statement.setTimestamp(3, watchDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<History> getAllHistoryData() {
        List<History> historyList = new ArrayList<>();
        String sql = "SELECT h.hid, h.watch_date, u.firstName, u.lastName, v.title " +
                     "FROM history h " +
                     "JOIN user u ON h.user_id = u.uid " +
                     "JOIN video v ON h.video_id = v.vid";

        try (PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                History history = new History();
                history.setHid(rs.getInt("hid"));
                String fullName = rs.getString("firstName") + " " + rs.getString("lastName");
                history.setUserFullName(fullName);  // Add full name to the History model
                history.setVideoTitle(rs.getString("title"));    // Set video title
                history.setWatchDate(rs.getTimestamp("watch_date"));
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }


    @Override
    public boolean addUser(User user) {
    	String sql = "INSERT INTO user (firstName, lastName, phoneNumber, gender, email, username, password, role, bio, profilePhoto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	try {
    			if (conn == null) {
                    System.err.println("Connection is null in addrow method.");
                    return false;
                }
    		PreparedStatement stmt = conn.prepareStatement(sql);
    	    stmt.setString(1, user.getFirstName());
    	    stmt.setString(2, user.getLastName());
    	    stmt.setString(3, user.getPhoneNumber());
    	    stmt.setString(4, user.getGender());
    	    stmt.setString(5, user.getEmail());
    	    stmt.setString(6, user.getUsername());
    	    stmt.setString(7, user.getPassword());
    	    stmt.setString(8, user.getRole());
    	    stmt.setString(9, user.getBio());
    	    stmt.setBytes(10, user.getProfilePhoto());
    	    int rowsInserted = stmt.executeUpdate();
    	    return rowsInserted > 0;
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	    return false;
    	}

    }
    
    @Override
	public boolean userUpdate(User u) {
    	String sql = "UPDATE user SET firstName=?, lastName=?, phoneNumber=?, gender=?, email=?, username=?, role=?, bio=?, profilePhoto=? WHERE uid=?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, u.getFirstName());
	        stmt.setString(2, u.getLastName());
	        stmt.setString(3, u.getPhoneNumber());
	        stmt.setString(4, u.getGender());
	        stmt.setString(5, u.getEmail());
	        stmt.setString(6, u.getUsername());
	        stmt.setString(7, u.getRole());
	        stmt.setString(8, u.getBio());
	        stmt.setBytes(9, u.getProfilePhoto());
	        stmt.setInt(10, u.getUid()); // Make sure to pass the user ID here

	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
    }
    
    @Override
    public User getUserById(int id) {
        User u = null; // Initialize the user object as null
        String sql = "SELECT * FROM user WHERE uid = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                u = new User();
                u.setUid(rs.getInt("uid")); // Correct field mapping
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setPhoneNumber(rs.getString("phoneNumber"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setBio(rs.getString("bio"));
                u.setProfilePhoto(rs.getBytes("profilePhoto"));
                return u;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    // Main Page
    
    @Override
    public List<Video> getAllApprovedVideoData() {
        List<Video> vList = new ArrayList<>();
        String sql = "SELECT v.*, u.firstname, u.lastname FROM video v JOIN user u ON v.uploaderid = u.uid WHERE v.isApproved = 1"; // Only approved videos

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Video v = new Video();
                v.setVid(rs.getInt("vid"));
                v.setTitle(rs.getString("title"));
                v.setVideoname(rs.getString("videoname"));
                v.setUploaderName(rs.getString("firstname") + " " + rs.getString("lastname"));
                v.setUploaderid(rs.getInt("uploaderid"));
                v.setViews(rs.getInt("views"));
                
                // Convert thumbnail bytes to Base64
                byte[] thumbnailBytes = rs.getBytes("thumbnail");
                if (thumbnailBytes != null) {
                    String base64Image = Base64.getEncoder().encodeToString(thumbnailBytes);
                    v.setBase64Thumbnail(base64Image);  // Set the Base64 image string
                }
                
                v.setLikes(rs.getInt("likes"));
                v.setDislikes(rs.getInt("dislikes"));
                v.setDescription(rs.getString("description"));
                v.setVideoLocation(rs.getString("videoLocation"));
                v.setUploadDate(rs.getString("uploadDate"));
                v.setCategory(rs.getString("category"));
                v.setApproved(rs.getBoolean("isApproved"));

                vList.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vList;
    }
    
    public List<Video> getRecommendedVideos() {
        List<Video> vList = new ArrayList<>();
        String sql = "SELECT v.*, u.firstname, u.lastname, u.profilephoto FROM video v JOIN user u ON v.uploaderid = u.uid WHERE v.isApproved = 1 ORDER BY RAND() LIMIT 6";
        
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Video v = new Video();
                v.setVid(rs.getInt("vid"));
                v.setTitle(rs.getString("title"));
                v.setVideoname(rs.getString("videoname"));
                v.setUploaderName(rs.getString("firstname") + " " + rs.getString("lastname"));
                v.setUploaderid(rs.getInt("uploaderid"));
                v.setViews(rs.getInt("views"));
                v.setThumbnail(rs.getBytes("thumbnail"));
                v.setUserProfileImage(rs.getBytes("profilephoto")); // Assuming the profile image is in BLOB
                v.setLikes(rs.getInt("likes"));
                v.setDislikes(rs.getInt("dislikes"));
                v.setDescription(rs.getString("description"));
                v.setVideoLocation(rs.getString("videoLocation"));
                v.setUploadDate(rs.getString("uploadDate"));
                v.setCategory(rs.getString("category"));
                v.setApproved(rs.getBoolean("isApproved"));

                vList.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vList;
    }

    public boolean updateUserAccount(User user) {
        String updateSQL = "UPDATE user SET firstName = ?, lastName = ?, phoneNumber = ?, gender = ?, bio = ?, profilePhoto = ? WHERE email = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getPhoneNumber());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getBio());

            // Check if profile photo is provided
            if (user.getProfilePhoto() != null) {
                pstmt.setBytes(6, user.getProfilePhoto());
            } else {
                pstmt.setNull(6, java.sql.Types.BLOB);
            }

            pstmt.setString(7, user.getEmail());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM user WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUid(rs.getInt("uid"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setGender(rs.getString("gender"));
                user.setEmail(rs.getString("email"));
                user.setBio(rs.getString("bio"));
                user.setProfilePhoto(rs.getBytes("profilePhoto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteVideo(int id) {
        String sql = "DELETE FROM video WHERE vid=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	

	@Override
	public Video getVideoById(int videoId) {
	    Video video = null;
	    String sql = "SELECT v.*, u.firstname, u.lastname FROM video v JOIN user u ON v.uploaderid = u.uid WHERE v.vid = ? AND v.isApproved = 1";

	    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
	        pstm.setInt(1, videoId);
	        ResultSet rs = pstm.executeQuery();

	        if (rs.next()) {
	            video = new Video();
	            video.setVid(rs.getInt("vid"));
	            video.setTitle(rs.getString("title"));
	            video.setVideoname(rs.getString("videoname"));
	            video.setUploaderName(rs.getString("firstname") + " " + rs.getString("lastname"));
	            video.setUploaderid(rs.getInt("uploaderid"));
	            video.setViews(rs.getInt("views"));

	            // Convert thumbnail bytes to Base64
	            byte[] thumbnailBytes = rs.getBytes("thumbnail");
	            if (thumbnailBytes != null) {
	                String base64Image = Base64.getEncoder().encodeToString(thumbnailBytes);
	                video.setBase64Thumbnail(base64Image);
	            }

	            video.setLikes(rs.getInt("likes"));
	            video.setDislikes(rs.getInt("dislikes"));
	            video.setDescription(rs.getString("description"));
	            video.setVideoLocation(rs.getString("videoLocation"));
	            video.setUploadDate(rs.getString("uploadDate"));
	            video.setCategory(rs.getString("category"));
	            video.setApproved(rs.getBoolean("isApproved"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return video;
	}
	
	@Override
	public void incrementVideoViews(int videoId) {
	    String sql = "UPDATE video SET views = views + 1 WHERE vid = ?";
	    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
	        pstm.setInt(1, videoId);
	        pstm.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void incrementLikes(int videoId) {
	    String sql = "UPDATE video SET likes = likes + 1 WHERE vid = ?";
	    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
	        pstm.setInt(1, videoId);
	        pstm.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void incrementDislikes(int videoId) {
	    String sql = "UPDATE video SET dislikes = dislikes + 1 WHERE vid = ?";
	    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
	        pstm.setInt(1, videoId);
	        pstm.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public List<Video> getUserVideos(int userId) {
	    List<Video> videoList = new ArrayList<>();
	    String sql = "SELECT v.*, u.firstname, u.lastname FROM video v " +
	                 "JOIN user u ON v.uploaderid = u.uid " +
	                 "WHERE v.uploaderid = ? AND v.isApproved = 1 " +
	                 "ORDER BY v.uploadDate DESC";

	    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
	        pstm.setInt(1, userId);
	        try (ResultSet rs = pstm.executeQuery()) {
	            while (rs.next()) {
	                Video v = new Video();
	                v.setVid(rs.getInt("vid"));
	                v.setTitle(rs.getString("title"));
	                v.setUploaderName(rs.getString("firstname") + " " + rs.getString("lastname"));
	                v.setUploaderid(rs.getInt("uploaderid"));
	                v.setViews(rs.getInt("views"));
	                v.setThumbnail(rs.getBytes("thumbnail"));
	                v.setLikes(rs.getInt("likes"));
	                v.setDislikes(rs.getInt("dislikes"));
	                v.setDescription(rs.getString("description"));
	                v.setVideoLocation(rs.getString("videoLocation"));
	                v.setUploadDate(rs.getString("uploadDate"));
	                v.setCategory(rs.getString("category"));
	                v.setApproved(rs.getBoolean("isApproved"));
	                videoList.add(v);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return videoList;
	}


	
	@Override
    public Integer getUserIdByEmail(String email) {
        Integer userId = null;
        String sql = "SELECT uid FROM user WHERE email = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("uid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
	
	@Override
	public List<Video> getUserVideoHistory(int userId) {
	    List<Video> videoHistoryList = new ArrayList<>();
	    String sql = "SELECT v.*, h.watch_date FROM history h JOIN video v ON h.video_id = v.vid WHERE h.user_id = ? ORDER BY h.watch_date DESC";
	    
	    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
	        pstm.setInt(1, userId);
	        ResultSet rs = pstm.executeQuery();
	        
	        while (rs.next()) {
	            Video video = new Video();
	            video.setVid(rs.getInt("vid"));
	            video.setTitle(rs.getString("title"));
	            video.setVideoname(rs.getString("videoname"));
	            video.setViews(rs.getInt("views"));
	            video.setUploadDate(rs.getString("uploadDate"));
	            video.setCategory(rs.getString("category"));
	            video.setWatchDate(rs.getTimestamp("watch_date"));
	            
	            // Fetch the thumbnail and convert to Base64 if it exists
	            byte[] thumbnailBytes = rs.getBytes("thumbnail");
	            if (thumbnailBytes != null && thumbnailBytes.length > 0) {
	                String base64Thumbnail = Base64.getEncoder().encodeToString(thumbnailBytes);
	                video.setBase64Thumbnail(base64Thumbnail);
	            }

	            videoHistoryList.add(video);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return videoHistoryList;
	}

	@Override
	public User getLoggedInUser(HttpSession session) {
	    String userEmail = (String) session.getAttribute("wel");
	    if (userEmail == null) {
	        return null; // No user logged in
	    }

	    UserController uc = new UserControllerImplements();
	    User user = uc.getUserByEmail(userEmail);

	    // Convert profile photo to Base64 if it exists
	    if (user != null && user.getProfilePhoto() != null) {
	        String base64ProfilePhoto = Base64.getEncoder().encodeToString(user.getProfilePhoto());
	        user.setBase64ProfilePhoto(base64ProfilePhoto);
	    }

	    return user;
	}

	@Override
    public List<Video> searchVideosByTitle(String query) {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT v.*, u.firstname, u.lastname FROM video v JOIN user u ON v.uploaderid = u.uid WHERE v.title LIKE ? AND v.isApproved = TRUE";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Video video = new Video();
                video.setVid(rs.getInt("vid"));
                video.setTitle(rs.getString("title"));
                video.setVideoname(rs.getString("videoname"));
                video.setUploaderName(rs.getString("firstname") + " " + rs.getString("lastname"));
                video.setUploaderid(rs.getInt("uploaderid"));
                video.setViews(rs.getInt("views"));

                // Convert thumbnail bytes to Base64
                byte[] thumbnailBytes = rs.getBytes("thumbnail");
                if (thumbnailBytes != null) {
                    String base64Image = Base64.getEncoder().encodeToString(thumbnailBytes);
                    video.setBase64Thumbnail(base64Image);
                }

                video.setLikes(rs.getInt("likes"));
                video.setDislikes(rs.getInt("dislikes"));
                video.setDescription(rs.getString("description"));
                video.setVideoLocation(rs.getString("videoLocation"));
                video.setUploadDate(rs.getString("uploadDate"));
                video.setCategory(rs.getString("category"));
                video.setApproved(rs.getBoolean("isApproved"));
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videos;
    }

    // Method to get videos by category
    @Override
    public List<Video> getVideosByCategory(String category) {
        List<Video> videos = new ArrayList<>();
        String sql = "SELECT v.*, u.firstname, u.lastname FROM video v JOIN user u ON v.uploaderid = u.uid WHERE v.category = ? AND v.isApproved = TRUE";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Video video = new Video();
                video.setVid(rs.getInt("vid"));
                video.setTitle(rs.getString("title"));
                video.setVideoname(rs.getString("videoname"));
                video.setUploaderName(rs.getString("firstname") + " " + rs.getString("lastname"));
                video.setUploaderid(rs.getInt("uploaderid"));
                video.setViews(rs.getInt("views"));

                // Convert thumbnail bytes to Base64
                byte[] thumbnailBytes = rs.getBytes("thumbnail");
                if (thumbnailBytes != null) {
                    String base64Image = Base64.getEncoder().encodeToString(thumbnailBytes);
                    video.setBase64Thumbnail(base64Image);
                }

                video.setLikes(rs.getInt("likes"));
                video.setDislikes(rs.getInt("dislikes"));
                video.setDescription(rs.getString("description"));
                video.setVideoLocation(rs.getString("videoLocation"));
                video.setUploadDate(rs.getString("uploadDate"));
                video.setCategory(rs.getString("category"));
                video.setApproved(rs.getBoolean("isApproved"));
                videos.add(video);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videos;
    }
    
    @Override
    public List<Video> sortVideosByViews(List<Video> videoList) {
        // Sort videos by views in descending order
        videoList.sort((v1, v2) -> Integer.compare(v2.getViews(), v1.getViews()));
        return videoList;
    }

    @Override
    public List<Video> getVideoData() {

        List<Video> vList = new ArrayList<>();
        String sql = "SELECT v.*, u.firstname, u.lastname FROM video v JOIN user u ON v.uploaderid = u.uid";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Video v = new Video();
                v.setVid(rs.getInt("vid"));
                v.setTitle(rs.getString("title"));
                v.setVideoname(rs.getString("videoname"));
                v.setUploaderName(rs.getString("firstname") + " " + rs.getString("lastname")); // Combine first and last names
                v.setUploaderid(rs.getInt("uploaderid"));
                v.setViews(rs.getInt("views"));
                v.setLikes(rs.getInt("likes"));
                v.setDislikes(rs.getInt("dislikes"));
                v.setDescription(rs.getString("description"));
                v.setVideoLocation(rs.getString("videoLocation"));
                v.setUploadDate(rs.getString("uploadDate"));
                v.setCategory(rs.getString("category"));
                v.setApproved(rs.getBoolean("isApproved"));

                vList.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vList;
    }

    @Override
    public boolean addComment(int userId, int videoId, String commentText) {
        String sql = "INSERT INTO comments (userId, videoId, commentText) VALUES (?, ?, ?)";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, userId);
            pstm.setInt(2, videoId);
            pstm.setString(3, commentText);

            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Comment> getCommentsByVideoId(int videoId) {
        List<Comment> commentList = new ArrayList<>();
        String sql = "SELECT c.*, u.username, u.profilePhoto FROM comments c JOIN user u ON c.userId = u.uid WHERE c.videoId = ? ORDER BY c.commentDate DESC";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, videoId);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("commentId"));
                comment.setVideoId(videoId);
                comment.setUserId(rs.getInt("userId"));
                comment.setUsername(rs.getString("username"));
                comment.setCommentText(rs.getString("commentText"));
                comment.setCommentDate(rs.getTimestamp("commentDate"));
                comment.setProfilePhoto(rs.getBytes("profilePhoto"));

                // Convert profile photo to Base64
                if (rs.getBytes("profilePhoto") != null) {
                    comment.setBase64ProfilePhoto(Base64.getEncoder().encodeToString(rs.getBytes("profilePhoto")));
                }

                commentList.add(comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentList;
    }


}
