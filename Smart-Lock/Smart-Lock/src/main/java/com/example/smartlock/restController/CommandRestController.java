package com.example.smartlock.restController;

import com.example.smartlock.entity.Door;
import com.example.smartlock.repository.DoorRepository;
import com.example.smartlock.service.DoorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/command", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommandRestController {

    private final DoorRepository repository;

    private final DoorService doorService;
    @GetMapping("/get")
    Optional<Door> getDoor(){

        return repository.findById(1L);
    }
    @PostMapping("/update")
    public void update(@RequestBody Door door){
        doorService.commandDoor(door);
    }


}
