package com.spiny.spiny_demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String mobile;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<Area> areas;

}

