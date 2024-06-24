package com.example.smartlock.service;

import com.example.smartlock.entity.Door;

public interface DoorService {
    void updateDoor(boolean status);
    void commandDoor(Door door);
}
