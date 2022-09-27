package com.igibgo.igibgo.entity;

public class BackstageUser {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Boolean isAdmin;
    private Boolean isDapesh;

    public BackstageUser(){

    }

    public BackstageUser(Long id, String username, String email, String password, Boolean isAdmin, Boolean isDapesh) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isDapesh = isDapesh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getDapesh() {
        return isDapesh;
    }

    public void setDapesh(Boolean dapesh) {
        isDapesh = dapesh;
    }
}
