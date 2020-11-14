package com.example.imageApi.AWSImageApi.profile;

import com.example.imageApi.AWSImageApi.dataStore.FakeProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfileDataAccessService {
    private final FakeProfileDataStore fakeProfileDataStore;

    @Autowired
    public ProfileDataAccessService(FakeProfileDataStore fakeProfileDataStore) {
        this.fakeProfileDataStore = fakeProfileDataStore;
    }

    List<UserProfile> getUserProfiles(){
        return fakeProfileDataStore.getUserProfiles();
    }
}
