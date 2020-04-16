package com.frankmoley.lil.learningspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frankmoley.lil.learningspring.business.domain.RoomReservation;
import com.frankmoley.lil.learningspring.business.service.ReservationService;
import com.frankmoley.lil.learningspring.web.DateUtils;

@Controller
public class RoomReservationController {

  private final ReservationService reservationService;

  @Autowired
  public RoomReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @GetMapping("/reservations")
  public String showRoomReservationByDate(
      @RequestParam(value = "date", required = false) String dateString, Model model) {
    List<RoomReservation> reservations = reservationService
        .getRoomReservationsForDate(DateUtils.createDateFromDateString(dateString));
    model.addAttribute("roomReservations", reservations);
    return "reservations";
  }
}
