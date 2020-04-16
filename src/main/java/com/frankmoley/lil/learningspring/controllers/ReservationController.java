package com.frankmoley.lil.learningspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frankmoley.lil.learningspring.data.entity.Reservation;
import com.frankmoley.lil.learningspring.data.repository.ReservationRepository;

@RestController
public class ReservationController {

  @Autowired
  ReservationRepository reservationRepository;

  @GetMapping("/reservationsAll")
  public Iterable<Reservation> getReservations() {
    return reservationRepository.findAll();
  }
}
