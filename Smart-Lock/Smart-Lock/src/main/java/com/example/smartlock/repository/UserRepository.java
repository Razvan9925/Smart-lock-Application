package com.example.smartlock.repository;

import com.example.smartlock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByEmail(String email);

    Optional<User> findUserByResetPasswordToken(String token);
    List<User> findAllByFirstNameIsContainingIgnoreCase(String keywords );

}