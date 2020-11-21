package com.example.imageApi.AWSImageApi.fileStore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {
    private final AmazonS3 amazonS3;

    @Autowired
    public FileStore(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }
    //Path is the bucket name + subfolders if we have
    public void save(String path, String fileName, Optional<Map<String,String>> optionalMethodData, InputStream inputStream){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMethodData.ifPresent(map ->{
            if(!map.isEmpty()){
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        try{
            amazonS3.putObject(path,fileName,inputStream,objectMetadata);
        }catch (AmazonServiceException e){
            throw new IllegalStateException("Failed to store file to s3", e);
        }
    }

    public byte[] download(String path,String key) {
        try{
            S3Object s3Object = amazonS3.getObject(path,key);
            return IOUtils.toByteArray(s3Object.getObjectContent());
        }catch (AmazonServiceException | IOException e){
            throw new IllegalStateException("Failed to download");
        }

    }
}
