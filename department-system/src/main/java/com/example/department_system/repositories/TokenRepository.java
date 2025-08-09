package com.example.department_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.department_system.models.TokenModel;

public interface TokenRepository extends JpaRepository<TokenModel, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE TokenModel t SET t.status = 'SERVED' WHERE t.id = :id")
    int markAsServed(@Param("id") Long id);
}
