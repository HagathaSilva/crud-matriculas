package com.example.crud_matricula.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/")
    public String home() {
        return "API Matricula rodando com sucesso!";
    }
}
