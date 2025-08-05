//package com.spiny.spiny_demo.Controller;
//
//import com.spiny.spiny_demo.Service.S3Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/images")
//public class ImageController {
//
//    private final S3Service s3Service;
//
//    @Autowired
//    public ImageController(S3Service s3Service) {
//        this.s3Service = s3Service;
//    }
//
//    @PostMapping("/{carId}/upload")
//    public ResponseEntity<String> uploadCarImage(
//            @PathVariable Long carId,
//            @RequestParam("file") MultipartFile file
//    ) {
//        try {
//            String imageUrl = s3Service.uploadFile(file, carId);
//            return ResponseEntity.ok("Image uploaded successfully: " + imageUrl);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Failed to upload image: " + e.getMessage());
//        }
//    }
//}
