package com.example.crud_matricula.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud_matricula.demo.entities.payment.Payment;
import com.example.crud_matricula.demo.entities.payment.PaymentRepository;
import com.example.crud_matricula.demo.entities.payment.PaymentRequestDTO;
import com.example.crud_matricula.demo.entities.payment.PaymentResponseDTO;
import com.example.crud_matricula.demo.entities.user.User;
import com.example.crud_matricula.demo.entities.user.UserRepository;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequestDTO dto) {
        Optional<User> optionalUser = userRepository.findById(dto.userId());
        if (optionalUser.isPresent()) {
            Payment payment = new Payment(dto, optionalUser.get());
            paymentRepository.save(payment);
            return ResponseEntity.ok("Pagamento processado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByUser(@PathVariable Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<PaymentResponseDTO> payments = paymentRepository
                .findAll()
                .stream()
                .filter(p -> p.getUser().getId().equals(userId))
                .map(PaymentResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(payments);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {
        List<PaymentResponseDTO> payments = paymentRepository.findAll()
                .stream()
                .map(PaymentResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(payments);
    }
}
