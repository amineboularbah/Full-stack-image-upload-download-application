package com.example.imageApi.AWSImageApi.profile;

import com.example.imageApi.AWSImageApi.bucket.BucketName;
import com.example.imageApi.AWSImageApi.fileStore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.apache.http.entity.ContentType.*;

@Service
public class ProfileService {

    private final ProfileDataAccessService profileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public ProfileService(ProfileDataAccessService profileDataAccessService,FileStore fileStore) {
        this.profileDataAccessService = profileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles(){
        return profileDataAccessService.getUserProfiles();
    }


    public void uploadProfileImage(UUID id, MultipartFile file) {
        //TODO check if image is not empty  DONE
        isFileEmpty(file);
        //TODO check if file is an image
        isFileAnImage(file);
        //TODO check if the user exists in database
        UserProfile user = getUserProfile(id);
        //TODO if user exists grab some metadata from file
        Map<String, String> metaData = extractMetaData(file);
        //TODO store image in S3 and update database with S3 image Link
        String path =String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getId());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try{
            fileStore.save(path,fileName, Optional.of(metaData),file.getInputStream());
            user.setProfileImageLink(fileName);
        }catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    public byte[] downloadUserProfileImage(UUID id) {
        UserProfile user = getUserProfile(id);
        String path =String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getId());
        return user.getProfileImageLink()
                .map(key -> fileStore.download(path,key)).orElse(new byte[0]);
    }

    private Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));
        return metaData;
    }

    private UserProfile getUserProfile(UUID id) {
        return profileDataAccessService.getUserProfiles()
                .stream()
                .filter((userProfile -> userProfile.getId().equals( id)))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", id)));
    }

    private void isFileAnImage(MultipartFile file) {
        if (!Arrays.asList(IMAGE_PNG.getMimeType(), IMAGE_JPEG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("Content type is not an image"+file.getContentType());
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("File is Empty");
        }
    }

}
