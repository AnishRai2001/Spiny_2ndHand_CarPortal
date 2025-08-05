//package com.spiny.spiny_demo.Service;
//
//import com.spiny.spiny_demo.Repository.CarRepository;
//import com.spiny.spiny_demo.entity.Car;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//
//import java.io.IOException;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class S3Service {
//
//    @Value("${aws.s3.bucket}")
//    private String bucketName;
//
//    @Value("${aws.region}")
//    private String region;
//    private final S3Client s3Client;
//    private final CarRepository carRepository;
//
//    public S3Service(S3Client s3Client, CarRepository carRepository) {
//        this.s3Client = s3Client;
//        this.carRepository = carRepository;
//    }
//
//    public String uploadFile(MultipartFile file, Long carId) throws IOException {
//        Car car = carRepository.findById(carId)
//                .orElseThrow(() -> new IllegalArgumentException("Car with ID " + carId + " not found."));
//
//        String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
//
//        PutObjectRequest putRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .contentType(file.getContentType())
//                .build();
//
//        s3Client.putObject(putRequest, RequestBody.fromBytes(file.getBytes()));
//
//        String imageUrl = "https://" + bucketName + ".s3.amazonaws.com/" + key;
//        car.setImageUrls(imageUrl);
//        carRepository.save(car);
//
//        return imageUrl;
//    }
//}
