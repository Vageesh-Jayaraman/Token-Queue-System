package com.example.tokenqueue.repositories;

import com.example.tokenqueue.models.TokenModel;
import com.example.tokenqueue.models.UserModel;
import com.example.tokenqueue.models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenModel, Long> {
    Optional<TokenModel> findByUser(UserModel user);
    Long countByDepartment(DepartmentModel department);
    Optional<TokenModel> findByUserAndDepartment(UserModel user, DepartmentModel department);
    List<TokenModel> findAllByUser(UserModel user);
}