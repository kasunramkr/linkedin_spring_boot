package com.example.demo.web;

import com.example.demo.business.service.RoomService;
import com.example.demo.data.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomWebServiceController {
    private final RoomService roomService;

    @Autowired
    public RoomWebServiceController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRoom(@RequestParam(name = "roomNumber", required = false) String roomNumber) {
        return roomService.getRoom(roomNumber);
    }

    @DeleteMapping
    public String deleteRoom(@RequestParam(name = "roomNumber") String roomNumber) {
        return roomService.deleteRoom(roomNumber);
    }

//    @PostMapping(consumes = "application/json", produces = "application/json")
//    public Room addRoom(@RequestBody Room room) {
//        return roomService.addRoom(room);
//    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Room addRoom(@RequestBody Room room) {
        return roomService.updateRoom(room);
    }
}