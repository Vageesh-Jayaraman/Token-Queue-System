package com.example.tokenqueue.repositories;

import com.example.tokenqueue.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByUsername(String username);
}

