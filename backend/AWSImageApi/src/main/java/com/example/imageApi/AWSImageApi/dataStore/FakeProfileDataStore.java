package com.example.imageApi.AWSImageApi.dataStore;

import com.example.imageApi.AWSImageApi.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("513b51df-1957-479e-ad5f-d6ec73a97f93"),"Patricia Wolf",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("13a105d3-45b7-47a5-bccb-ad46f72a6932"),"Georgina Kramer",null));

        USER_PROFILES.add(new UserProfile(UUID.fromString("8a6f6137-2ed8-4c27-aef1-0a566c989430"),"Sabrina Bryan",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("d772ce18-f2b5-48b1-a397-6a72efedb9d8"),"Mabel York",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("50602b6e-7ee3-4699-9ee7-8fc58ea86a1b"),"Anita Davila",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("64c3dda1-7a24-484d-86b4-70e5eef7cf67"),"Violet Hodges",null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }

}
