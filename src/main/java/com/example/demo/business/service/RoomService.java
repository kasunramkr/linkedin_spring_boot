package com.example.demo.business.service;

import com.example.demo.data.entity.Room;
import com.example.demo.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRoom(String roomNumber) {
        Iterable<Room> rooms;
        if (roomNumber == null) {
            rooms = this.roomRepository.findAll();
        } else {
            rooms = this.roomRepository.findRoomByRoomNumber(roomNumber);
        }
        return (List<Room>) rooms;
    }

    public String deleteRoom(String roomNumber) {

        Iterable<Room> room = this.roomRepository.findRoomByRoomNumber(roomNumber);
        if (room == null) {
            return "Room Not found";
        }
        else
        {
            this.roomRepository.delete( room.iterator().next());
            return "SUCCESS";
        }
    }

    }
