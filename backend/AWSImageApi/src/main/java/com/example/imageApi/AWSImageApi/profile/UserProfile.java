package com.example.imageApi.AWSImageApi.profile;

import java.util.Objects;
import java.util.UUID;

public class UserProfile {

    private UUID id;
    private String userName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(profileImageLink,that.profileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, profileImageLink);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImageLink() {
        return profileImageLink;
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
    }

    public UserProfile(UUID id, String userName, String profileImageLink) {
        this.id = id;
        this.userName = userName;
        this.profileImageLink = profileImageLink;
    }

    private String profileImageLink; //This is the S3 key
}
