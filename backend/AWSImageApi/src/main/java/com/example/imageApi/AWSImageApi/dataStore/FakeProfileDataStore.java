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
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"amineb",null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"groperevery",null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }

}
