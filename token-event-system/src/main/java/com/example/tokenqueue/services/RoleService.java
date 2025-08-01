package com.example.tokenqueue.services;

import com.example.tokenqueue.models.RoleModel;
import com.example.tokenqueue.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleModel addRole(RoleModel role) {
        return roleRepository.save(role);
    }

    public List<RoleModel> getAllRoles() {
        return roleRepository.findAll();
    }

    public RoleModel getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
