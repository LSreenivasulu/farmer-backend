package com.example.farmer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.farmer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}