package com.example.smartlock.service.impl;

import com.example.smartlock.entity.Door;
import com.example.smartlock.repository.DoorRepository;
import com.example.smartlock.service.DoorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoorServiceImpl implements DoorService {
    private final DoorRepository doorRepository;

    @Override
    public void updateDoor(boolean status) {
        Optional<Door> optionalDoor = doorRepository.findById(1L);
        Door door = optionalDoor.get();
        if(status){
            door.setStatus(1);
        }
        else {
            door.setStatus(0);
        }



        doorRepository.save(door);
    }

    @Override
    public void commandDoor(Door door) {
        Optional<Door> optionalDoor = doorRepository.findById(1L);
        Door door1 = optionalDoor.get();
        door1.setStatus(door.getStatus());
    }
}
