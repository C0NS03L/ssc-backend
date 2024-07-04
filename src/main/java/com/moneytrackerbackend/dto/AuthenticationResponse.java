package com.moneytrackerbackend.dto;

public class AuthenticationResponse {

    private String jwt;
    private String username;
    private Long userId;

    // Constructor
    public AuthenticationResponse(String jwt, String username, Long userId) {
        this.jwt = jwt;
        this.username = username;
        this.userId = userId;
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String token) {
        this.jwt = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
