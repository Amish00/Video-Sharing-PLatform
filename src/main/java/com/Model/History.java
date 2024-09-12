package com.Model;

import java.sql.Timestamp;

public class History {
	private int hid;
    private int userId;      // ID of the user who watched the video
    private int videoId;     // ID of the video that was watched
    private Timestamp watchDate;  // Timestamp when the video was watched
    
    private String userFullName;  // Add a field for user full name
    private String videoTitle; // Add a field for video title
    
    
    // Getters and Setters
    
    
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public Timestamp getWatchDate() {
		return watchDate;
	}
	public void setWatchDate(Timestamp watchDate) {
		this.watchDate = watchDate;
	}
	public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}


    
    
}
