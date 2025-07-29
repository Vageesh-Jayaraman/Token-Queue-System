package com.example.tokenqueue.repositories;

import com.example.tokenqueue.models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentModel, String> {
}
