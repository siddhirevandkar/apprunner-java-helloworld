package com.example.demo;

import java.util.List;
import java.util.ArrayList;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorld {
	
    Region region = Region.US_EAST_1;
    String bucketName = "apprunner-test-app1-java";
    
    ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
    S3Client s3 = S3Client.builder()
        .region(region)
        .credentialsProvider(credentialsProvider)
        .build();


    @GetMapping("/")
    public String index() {
        String s = "Hello Citi Team from AWS App Runner. The JDK version is " + System.getProperty("java.version");
        return s;
    }
    
    @GetMapping("/s3")
    public List<String> GetObject(){
//    	return "Hello S3";
    	ListObjectsRequest listObjects = ListObjectsRequest
                .builder()
                .bucket(bucketName)
                .build();

            ListObjectsResponse res = s3.listObjects(listObjects);
            List<S3Object> objects = res.contents();
            List<String> objectsStr = new ArrayList<String>();
            for (S3Object myValue : objects) {
            	objectsStr.add(myValue.key());
//                System.out.print("\n The name of the key is " + myValue.key());
//                System.out.print("\n The object is " + calKb(myValue.size()) + " KBs");
//                System.out.print("\n The owner is " + myValue.owner());
            }
            return objectsStr;
    }
    
    @GetMapping("/ddb")
    public String GetItem(){
    	return "Hello Dynamo";
    }

}

