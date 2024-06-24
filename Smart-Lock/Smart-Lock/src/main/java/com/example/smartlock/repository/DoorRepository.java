package com.example.smartlock.repository;

import com.example.smartlock.entity.Door;
import com.example.smartlock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorRepository  extends JpaRepository<Door, Long> {

}
