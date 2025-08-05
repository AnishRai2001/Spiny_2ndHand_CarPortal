package com.spiny.spiny_demo.Service;

import com.spiny.spiny_demo.Repository.CarRepository;
import com.spiny.spiny_demo.entity.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service  // Marks this class as a Spring service component
public class S3MultiService {

    // Loads AWS region from application.properties/yml
    @Value("${aws.region}")
    private String region;

    // Loads the S3 bucket name from configuration
    @Value("${aws.s3.bucket}")
    private String bucketName;

    // AWS S3 client to perform file uploads
    private final S3Client s3Client;

    // Repository to interact with Car entity
    private final CarRepository carRepository;

    // Constructor-based dependency injection
    public S3MultiService(CarRepository carRepository, S3Client s3Client) {
        this.carRepository = carRepository;
        this.s3Client = s3Client;
    }

    /**
     * Uploads a list of image files to AWS S3, associates their URLs with a Car, and returns the list of URLs.
     *
     * @param files List of image files (MultipartFile)
     * @param carId ID of the car to associate the images with
     * @return List of S3 public image URLs
     * @throws IOException if reading file bytes fails
     */
    public List<String> uploadEvaluationPhoto(List<MultipartFile> files, long carId) throws IOException {

        // Fetch the Car entity by ID, or throw error if not found
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("CarId not found"));

        // List to collect uploaded image URLs
        List<String> uploadUrls = new ArrayList<>();

        // Loop through each uploaded file
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;  // Skip empty files

            // Generate a unique key for each file to avoid filename collision
            String key = UUID.randomUUID() + "-" + file.getOriginalFilename();

            // Build the S3 upload request with necessary metadata
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)                     // S3 bucket name
                    .key(key)                               // Unique file key in S3
                    .contentType(file.getContentType())     // MIME type of the file
                    .acl("public-read")                     // Make the file publicly accessible
                    .build();

            // Upload the file to S3
            s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

            // Build the public URL of the uploaded file
            String url = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;

            // Add the URL to the return list
            uploadUrls.add(url);

            // Add the URL to the Car entity (assumes Car has a List<String> imageUrls)
            car.getImageUrls().add(url);
        }

        // Save the updated Car entity with new image URLs to the database
        carRepository.save(car);

        // Return the list of uploaded image URLs
        return uploadUrls;
    }
}
