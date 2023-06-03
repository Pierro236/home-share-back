package com.homeshare.homeshareapi.repository;

import com.homeshare.homeshareapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
