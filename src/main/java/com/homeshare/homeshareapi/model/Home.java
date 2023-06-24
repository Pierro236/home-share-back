package com.homeshare.homeshareapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "HOME")
@Getter
@Setter
@NoArgsConstructor
public class Home {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 50)
    private String title;

    @Column(length = 100)
    private String description;

    @Column
    private boolean isShared;

    @Column(length = 250)
    private String image1;
    @Column(length = 250)

    private String image2;
    @Column(length = 250)

    private String image3;

    @Column
    private String startRent;

    @Column
    private String endRent;

    @Column(length = 350)
    private String timing;
}
