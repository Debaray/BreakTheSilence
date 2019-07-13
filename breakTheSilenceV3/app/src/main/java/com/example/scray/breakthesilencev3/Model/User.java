package com.example.scray.breakthesilencev3.Model;

public class User {

    private  String latitude;
    private String longitude;
    private String email;
    private String id;
    private String imageURL;
    private Long online;
    private String userStatus;
    private String search;
    private String status;
    private String thumb_image;
    private String username;

    public User(String latitude, String longitude, String email, String id, String imageURL, Long online, String userStatus, String search, String status, String thumb_image, String username) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.email = email;
        this.id = id;
        this.imageURL = imageURL;
        this.online = online;
        this.userStatus = userStatus;
        this.search = search;
        this.status = status;
        this.thumb_image = thumb_image;
        this.username = username;
    }

    public User() {

    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getOnline() {
        return online;
    }

    public void setOnline(Long online) {
        this.online = online;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumb_image() {
        return thumb_image;
    }

    public void setThumb_image(String thumb_image) {
        this.thumb_image = thumb_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
