package com.homeshare.homeshareapi.service;

import com.homeshare.homeshareapi.model.Reservation;
import com.homeshare.homeshareapi.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> read() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation modify(UUID id, Reservation reservation) {
        return reservationRepository.findById(id)
                .map(p -> {
                    p.setHome(reservation.getHome());
                    p.setUser(reservation.getUser());
                    p.setStartDate(reservation.getStartDate());
                    p.setEndDate(reservation.getEndDate());
                    return reservationRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Reservation not Existed !"));
    }

    @Override
    public String delete(UUID id) {
        reservationRepository.deleteById(id);
        return "Reservation Delete !";
    }

    @Override
    public Reservation getReservationById(UUID id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }
}
