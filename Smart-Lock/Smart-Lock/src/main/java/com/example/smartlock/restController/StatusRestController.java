package com.example.smartlock.restController;

import com.example.smartlock.entity.Door;
import com.example.smartlock.entity.Status;
import com.example.smartlock.repository.StatusRepository;
import com.example.smartlock.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/status", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatusRestController {
    private final StatusService service;
    private final StatusRepository repository;

    @GetMapping("/get")
    public Optional<Status> get(){

        return repository.findById(1L);

    }

    @PostMapping("/update")
    public void update(@RequestBody Status status){
        service.updateStatus(status);
    }
}
