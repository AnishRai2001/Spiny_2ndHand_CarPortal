package com.spiny.spiny_demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int pincode;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;


}



