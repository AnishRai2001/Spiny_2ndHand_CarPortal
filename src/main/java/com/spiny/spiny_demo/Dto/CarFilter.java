package com.spiny.spiny_demo.Dto;
import lombok.Data;

@Data
public class CarFilter {
    private String brand;
    private String model;
    private Double minPrice;
    private Double maxPrice;
    private Integer minYear;
    private Integer maxYear;
    private Integer minKmDriven;
    private Integer maxKmDriven;
    private String fuelType;
    private Integer minOwnerCount;
    private Integer maxOwnerCount;
    private Boolean approved;
    private String sellerUsername;
}
