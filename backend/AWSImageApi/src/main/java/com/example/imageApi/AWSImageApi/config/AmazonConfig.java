package com.example.imageApi.AWSImageApi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.imageApi.AWSImageApi.projectKeys.AWSKeys.*;

//This class will provide us with the aws S3 client
@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 s3(){
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                AWS_ACCESS_KEY,AWS_SECRET_KEY
        );
        return AmazonS3ClientBuilder.standard().withRegion(REGION).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }
}
