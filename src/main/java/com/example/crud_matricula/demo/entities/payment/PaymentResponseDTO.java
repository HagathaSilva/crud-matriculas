package com.example.crud_matricula.demo.entities.payment;

import java.time.LocalDateTime;

public record PaymentResponseDTO(Long id, Double amount, LocalDateTime timestamp) {

    public PaymentResponseDTO(Payment payment) {
        this(payment.getId(), payment.getAmount(), payment.getTimestamp());
    }
}