package com.spiny.spiny_demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "app_user") // Avoid reserved word 'user'
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password; // Should be hashed

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
