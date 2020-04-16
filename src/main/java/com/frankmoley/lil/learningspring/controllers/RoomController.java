package com.frankmoley.lil.learningspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frankmoley.lil.learningspring.data.entity.Room;
import com.frankmoley.lil.learningspring.data.repository.RoomRepository;

@RestController
public class RoomController {

  @Autowired
  private RoomRepository roomRepository;

  @GetMapping(value = "/rooms")
  public Iterable<Room> getRooms() {
    return roomRepository.findAll();
  }
}
