package com.wootechco.Makey.profile.dto;

public class ProfileResponseDto {
    private String username;
    private String email;
    private String bio;

    public ProfileResponseDto(String username, String email, String bio) {
        this.username = username;
        this.email = email;
        this.bio = bio;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }
}
