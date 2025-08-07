package com.spiny.spiny_demo.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CustomerDTO {
    private int id;
    private String name;
    private String mobile;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private int pincode;
    private LocalDate dateOfVisit;
    private LocalTime timeOfVisit;
}


