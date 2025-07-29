package com.example.tokenqueue.services;

import com.example.tokenqueue.dtos.UserDTO;
import com.example.tokenqueue.models.UserModel;
import com.example.tokenqueue.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        UserModel user = new UserModel();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); 

        UserModel savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getUsername(), savedUser.getPassword());
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getUsername(), user.getPassword()))
                .collect(Collectors.toList());
    }
}
