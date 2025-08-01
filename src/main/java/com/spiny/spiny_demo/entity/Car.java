package com.spiny.spiny_demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Column(name = "title", nullable = false, length = 255)
//    private String title;

    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "km_driven", nullable = false)
    private int kmDriven;

    @Column(name = "fuel_type", nullable = false, length = 50)
    private String fuelType;

    @Column(name = "transmission", nullable = false, length = 50)
    private String transmission;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "owner_count", nullable = false)
    private int ownerCount;

    @ElementCollection
    @CollectionTable(name = "car_images", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @Column(name = "status", nullable = false, length = 50)
    private String status;  // e.g., "available", "sold", etc.

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;




//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "listed_by_user_id", nullable = false)
//    private User listedBy;

//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//
//    @JoinColumn(name = "listed_by_id", nullable = false)
//    private User listedBy;

}
