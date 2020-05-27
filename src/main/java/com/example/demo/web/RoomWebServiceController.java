package com.example.demo.web;

import com.example.demo.business.service.RoomService;
import com.example.demo.data.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("room")
public class RoomWebServiceController {
    private final RoomService roomService;

    @Autowired
    public RoomWebServiceController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRoomReservations(@RequestParam(name = "roomNumber", required = false) String roomNumber) {
        return roomService.getRoom(roomNumber);
    }

    @DeleteMapping
    public String deleteRoomReservations(@RequestParam(name = "roomNumber") String roomNumber) {
        return roomService.deleteRoom(roomNumber);
    }
}