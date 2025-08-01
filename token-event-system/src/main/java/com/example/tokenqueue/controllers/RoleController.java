package com.example.tokenqueue.controllers;

import com.example.tokenqueue.models.RoleModel;
import com.example.tokenqueue.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public RoleModel addRole(@RequestBody RoleModel role) {
        return roleService.addRole(role);
    }

    @GetMapping
    public List<RoleModel> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleModel getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }
}
