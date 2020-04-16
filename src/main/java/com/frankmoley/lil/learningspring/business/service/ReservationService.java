package com.frankmoley.lil.learningspring.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frankmoley.lil.learningspring.business.domain.RoomReservation;
import com.frankmoley.lil.learningspring.data.entity.Guest;
import com.frankmoley.lil.learningspring.data.entity.Reservation;
import com.frankmoley.lil.learningspring.data.entity.Room;
import com.frankmoley.lil.learningspring.data.repository.GuestRepository;
import com.frankmoley.lil.learningspring.data.repository.ReservationRepository;
import com.frankmoley.lil.learningspring.data.repository.RoomRepository;

@Service
public class ReservationService {

  private final RoomRepository roomRepository;
  private final GuestRepository guestRepository;
  private final ReservationRepository reservationRepository;

  @Autowired
  public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository,
      ReservationRepository reservationRepository) {
    this.roomRepository = roomRepository;
    this.guestRepository = guestRepository;
    this.reservationRepository = reservationRepository;
  }

  public List<RoomReservation> getRoomReservationsForDate(Date date) {
    Iterable<Room> rooms = roomRepository.findAll();

    Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
    rooms.forEach(room -> {
      RoomReservation roomReservation = new RoomReservation();
      roomReservation.setRoomId(room.getRoomId());
      roomReservation.setRoomName(room.getRoomName());
      roomReservation.setRoomNumber(room.getRoomNumber());
      roomReservationMap.put(room.getRoomId(), roomReservation);
    });

    Iterable<Reservation> reservations = reservationRepository
        .findReservationByReservationDate(new java.sql.Date(date.getTime()));
    reservations.forEach(reservation -> {
      RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
      roomReservation.setDate(date);
      Guest guest = guestRepository.findById(reservation.getGuestId()).get();
      roomReservation.setFirstName(guest.getFirstName());
      roomReservation.setLastName(guest.getLastName());
      roomReservation.setGuestId(guest.getGuestId());
    });

    List<RoomReservation> roomReservations = new ArrayList<>();
    for (Long id : roomReservationMap.keySet()) {
      roomReservations.add(roomReservationMap.get(id));
    }

    roomReservations.sort((rr1, rr2) -> rr1.getRoomName().compareTo(rr2.getRoomName()));

    return roomReservations;
  }

  public List<Guest> getHotelGuests() {
    Iterable<Guest> guests = this.guestRepository.findAll();
    List<Guest> guestList = new ArrayList<>();
    guests.forEach(guest -> {
      guestList.add(guest);
    });

    guestList.sort((g1, g2) -> g1.getLastName().compareTo(g2.getLastName()));

    return guestList;
  }
}
