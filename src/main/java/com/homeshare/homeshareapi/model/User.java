package com.homeshare.homeshareapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private UUID userid;
    @Column(length = 50)
    private String lastname;
    @Column(length = 100)
    private String firstname;
    @Column(length = 150)
    private String email;
    @Column(length = 200)
    private String address;
    @Column(length = 250)
    private String password;

}
