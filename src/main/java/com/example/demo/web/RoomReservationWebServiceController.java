package com.example.demo.web;

import com.example.demo.business.domain.RoomReservation;
import com.example.demo.business.service.RoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class RoomReservationWebServiceController {
    private final RoomReservationService roomReservationService;

    @Autowired
    public RoomReservationWebServiceController(RoomReservationService roomReservationService) {
        this.roomReservationService = roomReservationService;
    }

    @GetMapping
    public List<RoomReservation> getRoomReservations(@RequestParam(name = "date", required = false) String dateString) {
        Date date = DateUtils.createDateFromDateString(dateString);
        return roomReservationService.getRoomReservationForDate(date);
    }
}
