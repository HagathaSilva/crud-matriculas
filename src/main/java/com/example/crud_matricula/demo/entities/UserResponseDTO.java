package com.example.crud_matricula.demo.entities;

public record UserResponseDTO(Long id, String name, String cpf, String email) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getCpf(), user.getEmail());

    }

}
