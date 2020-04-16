package com.frankmoley.lil.learningspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frankmoley.lil.learningspring.data.entity.Guest;
import com.frankmoley.lil.learningspring.data.repository.GuestRepository;

@RestController
public class GuestController {

  @Autowired
  GuestRepository guestRepository;

  @GetMapping("/guestsRest")
  public Iterable<Guest> getGuests() {
    return guestRepository.findAll();
  }
}
