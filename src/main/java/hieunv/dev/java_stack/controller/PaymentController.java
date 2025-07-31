package hieunv.dev.java_stack.controller;

import hieunv.dev.java_stack.dto.PaymentRequest;
import hieunv.dev.java_stack.dto.PaymentResponse;
import hieunv.dev.java_stack.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        try {
            String idempotencyKey = request.getIdempotencyKey() != null ?
                    request.getIdempotencyKey()
                    : UUID.randomUUID().toString();

            PaymentResponse response = paymentService.createPayment(request, idempotencyKey);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
