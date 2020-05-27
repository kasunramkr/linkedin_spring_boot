package com.example.demo.business.service;

import com.example.demo.business.domain.RoomReservation;
import com.example.demo.data.entity.Guest;
import com.example.demo.data.entity.Reservation;
import com.example.demo.data.entity.Room;
import com.example.demo.data.repository.GuestRepository;
import com.example.demo.data.repository.ReservationRepository;
import com.example.demo.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public RoomReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservation(Date date) {
        Iterable<Reservation> reservations;
        if (date == null) {
            reservations = this.reservationRepository.findAll();
        } else {
            reservations = this.reservationRepository.findReservationByResDate(new java.sql.Date(date.getTime()));
        }
        return loadRoomReservationDetails(reservations);
    }

    private List<RoomReservation> loadRoomReservationDetails(Iterable<Reservation> reservations) {
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            RoomReservation roomReservation = new RoomReservation();
            Room room = this.roomRepository.findById(reservation.getRoomId()).get();
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setDate(reservation.getResDate());

            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setGuestId(guest.getGuestId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());

            roomReservations.add(roomReservation);
        }
        return roomReservations;
    }
}
