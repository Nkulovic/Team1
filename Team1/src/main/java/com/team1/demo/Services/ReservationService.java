package com.team1.demo.Services;

import com.team1.demo.Entity.Reservation;
import com.team1.demo.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
