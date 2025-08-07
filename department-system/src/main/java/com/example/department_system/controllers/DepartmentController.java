package com.example.department_system.controllers;

import com.example.department_system.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{departmentId}")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public Object getAllTokens(@PathVariable String departmentId) {
        return departmentService.getAllTokens(departmentId);
    }

    @GetMapping("/")
    public Object getTop(@PathVariable String departmentId) {
        return departmentService.getTop(departmentId);
    }

    @PostMapping("/")
    public Object serveTop(@PathVariable String departmentId) {
        return departmentService.serveTop(departmentId);
    }
}
