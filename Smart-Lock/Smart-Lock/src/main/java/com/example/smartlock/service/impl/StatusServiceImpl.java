package com.example.smartlock.service.impl;

import com.example.smartlock.entity.Status;
import com.example.smartlock.repository.StatusRepository;
import com.example.smartlock.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    public void updateStatus(Status status) {
        Optional<Status> optionalStatus = statusRepository.findById(1L);
        Status status1 = optionalStatus.get();
        status1.setProblem(status.getProblem());
        statusRepository.save(status1);

    }
}
