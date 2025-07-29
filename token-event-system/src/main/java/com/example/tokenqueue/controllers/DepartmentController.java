package com.example.tokenqueue.controllers;

import com.example.tokenqueue.dtos.DepartmentDTO;
import com.example.tokenqueue.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public List<DepartmentDTO> getDepartments() {
        return departmentService.getDepartments();
    }

    @PostMapping("/department")
    public String addDepartment(@RequestBody DepartmentDTO dto) {
        return departmentService.addDepartment(dto);
    }
}
