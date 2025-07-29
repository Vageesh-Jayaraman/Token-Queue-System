package com.example.tokenqueue.services;

import com.example.tokenqueue.dtos.DepartmentDTO;
import com.example.tokenqueue.models.DepartmentModel;
import com.example.tokenqueue.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentDTO> getDepartments() {
        List<DepartmentModel> departments = departmentRepository.findAll();
        return departments.stream()
                .map(d -> new DepartmentDTO(d.getDepartment_id(), d.getName()))
                .collect(Collectors.toList());
    }

    public String addDepartment(DepartmentDTO dto) {
        DepartmentModel department = new DepartmentModel(dto.getDepartment_id(), dto.getDepartment());
        departmentRepository.save(department);
        return "Department added successfully";
    }
}
