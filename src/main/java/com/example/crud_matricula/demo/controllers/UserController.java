package com.example.crud_matricula.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud_matricula.demo.entities.user.User;
import com.example.crud_matricula.demo.entities.user.UserRepository;
import com.example.crud_matricula.demo.entities.user.UserRequestDTO;
import com.example.crud_matricula.demo.entities.user.UserResponseDTO;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void createUser(@RequestBody UserRequestDTO userData) {
        User user = new User(userData);
        userRepository.save(user);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDTO::new).toList();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserResponseDTO> getUserByCpf(@PathVariable String cpf) {
        return userRepository.findByCpf(cpf)
                .map(user -> ResponseEntity.ok(new UserResponseDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }
}
