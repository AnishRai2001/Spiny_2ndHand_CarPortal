package com.spiny.spiny_demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String mobile;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private int pincode;
    private LocalDate dateOfVisit;
    private LocalTime timeOfVisit;


    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent assignedAgent;
}
