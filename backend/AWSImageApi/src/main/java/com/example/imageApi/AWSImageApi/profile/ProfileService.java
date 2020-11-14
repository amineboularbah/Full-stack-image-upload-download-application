package com.example.imageApi.AWSImageApi.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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


    public void uploadProfileImage(UUID id, MultipartFile file) {
        //TODO check if image is not empty
        //TODO check if file is an image
        //TODO check if the user exists in database
        //TODO if user exists grab some metadata from file
        //TODO store image in S3 and update database with S3 image Link
    }
}
