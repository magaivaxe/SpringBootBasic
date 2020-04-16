package com.frankmoley.lil.learningspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.frankmoley.lil.learningspring.business.service.ReservationService;
import com.frankmoley.lil.learningspring.data.entity.Guest;

@Controller
public class GuestWebController {

  private final ReservationService reservationService;

  @Autowired
  public GuestWebController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @GetMapping("/guests")
  public String getGuests(Model model) {
    List<Guest> guests = reservationService.getHotelGuests();
    model.addAttribute("allGuests", guests);
    return "guests";
  }
}
