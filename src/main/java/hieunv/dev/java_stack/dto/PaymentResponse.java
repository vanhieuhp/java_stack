package hieunv.dev.java_stack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentResponse {

    private Long id;
    private String userId;
    private Double amount;
    private LocalDateTime createdAt;
    private String idempotencyKey;
}
