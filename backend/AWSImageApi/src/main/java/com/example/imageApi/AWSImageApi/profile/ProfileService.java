package com.example.imageApi.AWSImageApi.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileDataAccessService profileDataAccessService;

    @Autowired
    public ProfileService(ProfileDataAccessService profileDataAccessService) {
        this.profileDataAccessService = profileDataAccessService;
    }

    List<UserProfile> getUserProfiles(){
        return profileDataAccessService.getUserProfiles();
    }


}
