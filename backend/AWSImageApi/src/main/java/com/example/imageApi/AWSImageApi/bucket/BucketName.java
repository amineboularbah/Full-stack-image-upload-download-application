package com.example.imageApi.AWSImageApi.bucket;

import static com.example.imageApi.AWSImageApi.projectKeys.AWSKeys.BUCKET_NAME;

public enum BucketName {
    PROFILE_IMAGE(BUCKET_NAME);

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    public String getBucketName(){
        return bucketName;
    }
}
