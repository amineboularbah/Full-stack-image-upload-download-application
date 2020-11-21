package com.example.imageApi.AWSImageApi.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api-v1/userProfile")
@CrossOrigin("*")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles(){
        return profileService.getUserProfiles();
    }

    @PostMapping(
            path = "{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadProfileImage(@PathVariable("id")UUID id, @RequestParam("file")MultipartFile file){
    profileService.uploadProfileImage(id,file);
    }

    @GetMapping("{id}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("id")UUID id){
    return profileService.downloadUserProfileImage(id);
    }
}
