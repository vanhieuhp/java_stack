package hieunv.dev.java_stack.service;

import hieunv.dev.java_stack.dto.PaymentRequest;
import hieunv.dev.java_stack.dto.PaymentResponse;
import hieunv.dev.java_stack.entity.Payment;
import hieunv.dev.java_stack.repo.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Log4j2
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentResponse createPayment(PaymentRequest request, String idempotencyKey) {
        // Check if idempotency key already used
        Payment existing = paymentRepository.findByIdempotencyKey(idempotencyKey);
        if (existing != null) {
            return PaymentResponse.builder()
                    .id(existing.getId())
                    .userId(existing.getUserId())
                    .amount(existing.getAmount())
                    .createdAt(existing.getCreatedAt())
                    .idempotencyKey(existing.getIdempotencyKey())
                    .build();
        }

        // Create new payment
        Payment payment = new Payment();
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setIdempotencyKey(idempotencyKey);
        payment.setCreatedAt(LocalDateTime.now());

        payment = paymentRepository.save(payment);

        return PaymentResponse.builder()
                .id(payment.getId())
                .userId(payment.getUserId())
                .amount(payment.getAmount())
                .createdAt(payment.getCreatedAt())
                .idempotencyKey(payment.getIdempotencyKey())
                .build();
    }
}
