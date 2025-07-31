//package com.spiny.spiny_demo.config;
//
//import org.springframework.context.annotation.Bean;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//
//public class S3Config {
//    private String region;
//    private String accesskey;
//    private String secretKey;
//
//    @Bean
//    public S3Client s3Client;
//        AwsBasicCredentials credentials=AwsBasicCredentials.create(accesskey,secretKey){
//        return S3Client.builder()
//                .region(Region.of(region))
//                .credentialsProvider(StaticCredentialsProvider.create(credentials))
//                .build();
//    }
//}
