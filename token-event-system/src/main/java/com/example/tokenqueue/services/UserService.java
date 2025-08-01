package com.example.tokenqueue.services;

import com.example.tokenqueue.dtos.UserDTO;
import com.example.tokenqueue.models.DepartmentModel;
import com.example.tokenqueue.models.RoleModel;
import com.example.tokenqueue.models.UserModel;
import com.example.tokenqueue.repositories.DepartmentRepository;
import com.example.tokenqueue.repositories.RoleRepository;
import com.example.tokenqueue.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        RoleModel role = roleRepository.findById(userDTO.getRole_id())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        boolean isEmployee = role.getName().equalsIgnoreCase("EMPLOYEE");

        if (isEmployee && userDTO.getDepartment_id() == null) {
            throw new RuntimeException("EMPLOYEE must be assigned a department");
        }
        if (!isEmployee && userDTO.getDepartment_id() != null) {
            throw new RuntimeException("NON-EMPLOYEE users should not have a department");
        }

        DepartmentModel department = null;
        if (userDTO.getDepartment_id() != null) {
            department = departmentRepository.findById(userDTO.getDepartment_id())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
        }

        UserModel user = new UserModel();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); 
        user.setEmail(userDTO.getEmail());
        user.setRole(role);
        user.setDepartment(department);

        UserModel savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO mapToDTO(UserModel user) {
        return new UserDTO(
                user.getUsername(),
                null, 
                user.getEmail(),
                user.getRole().getRole_id(),
                user.getDepartment() != null ? user.getDepartment().getDepartment_id() : null
        );
    }
}
