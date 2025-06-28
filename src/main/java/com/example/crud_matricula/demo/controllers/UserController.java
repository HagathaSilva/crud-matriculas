package com.example.crud_matricula.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud_matricula.demo.entities.User;
import com.example.crud_matricula.demo.entities.UserRepository;
import com.example.crud_matricula.demo.entities.UserRequestDTO;
import com.example.crud_matricula.demo.entities.UserResponseDTO;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @PostMapping
    public void createUser(@RequestBody UserRequestDTO userData) {
        User user = new User(userData);
        userRepository.save(user);
        return;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {

        List<UserResponseDTO> userList = userRepository.findAll().stream().map(UserResponseDTO::new).toList();
        return userList;
    }

}
