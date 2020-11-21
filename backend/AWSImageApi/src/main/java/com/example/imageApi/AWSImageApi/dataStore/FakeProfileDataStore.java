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
        USER_PROFILES.add(new UserProfile(UUID.fromString("513b51df-1957-479e-ad5f-d6ec73a97f93"),"amineb",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("13a105d3-45b7-47a5-bccb-ad46f72a6932"),"groperevery",null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }

}
