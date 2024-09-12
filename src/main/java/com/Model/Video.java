package com.Model;

import java.sql.Timestamp;
import java.util.Base64;

public class Video {
    private int vid;  
    private String title;
    private String videoname;
    private String uploaderName; 
    private int uploaderid;  
    private int views;
    private byte[] thumbnail; // Changed to byte[] to store the image directly in the database
    private int likes;
    private int dislikes;
    private String description;
    private String videoLocation;
    private String uploadDate;
    private boolean isApproved;
    private String category;
    
    private String base64Thumbnail;
    private String userProfileBase64;
    private byte[] userProfileImage;
    
    private Timestamp watchDate;

    // Getters and Setters
    
    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public int getUploaderid() {
        return uploaderid;
    }

    public void setUploaderid(int uploaderid) {
        this.uploaderid = uploaderid;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
    	 this.thumbnail = thumbnail;
         if (thumbnail != null) {
             this.base64Thumbnail = Base64.getEncoder().encodeToString(thumbnail);
         }// Convert byte[] to Base64 String
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoLocation() {
        return videoLocation;
    }

    public void setVideoLocation(String videoLocation) {
        this.videoLocation = videoLocation;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
    
    public String getBase64Thumbnail() {
        return base64Thumbnail;
    }

    public void setBase64Thumbnail(String base64Thumbnail) {
        this.base64Thumbnail = base64Thumbnail;
    }
    
    public byte[] getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(byte[] userProfileImage) {
        this.userProfileImage = userProfileImage;
    }
    
    public String getUserProfileBase64() {
        return userProfileBase64;
    }

    public void setUserProfileBase64(String userProfileBase64) {
        this.userProfileBase64 = userProfileBase64;
    }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
    
	public Timestamp getWatchDate() {
	    return watchDate;
	}

	public void setWatchDate(Timestamp watchDate) {
	    this.watchDate = watchDate;
	}
    
}
