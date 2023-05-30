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

    @Column
    private Byte[] image1;
    @Column
    private Byte[] image2;
    @Column
    private Byte[] image3;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startRent;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endRent;

    @Column(length = 350)
    private String timing;
}
