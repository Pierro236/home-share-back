package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    Reservation create(Reservation reservation);

    List<Reservation> read();

    Reservation modify(UUID id, Reservation reservation);

    String delete(UUID id);

    Reservation getReservationById(UUID id);
}
