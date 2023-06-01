package com.homeshare.homeshareapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "RATING")
@Getter
@Setter
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue
    private UUID ratingId;
    @Column
    private UUID userId;
    @Column
    private UUID homeId;
    @Column
    private int ratingUserHome;
}
