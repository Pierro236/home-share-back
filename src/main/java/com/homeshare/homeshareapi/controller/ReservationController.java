package com.homeshare.homeshareapi.controller;

import com.homeshare.homeshareapi.model.Reservation;
import com.homeshare.homeshareapi.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create")
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.create(reservation);
    }

    @GetMapping("/read")
    public List<Reservation> read() {
        return reservationService.read();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable UUID id) {
        return reservationService.getReservationById(id);
    }

    @PutMapping("/update/{id}")
    public Reservation update(@PathVariable UUID id, @RequestBody Reservation reservation) {
        return reservationService.modify(id, reservation);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        return reservationService.delete(id);
    }
}

