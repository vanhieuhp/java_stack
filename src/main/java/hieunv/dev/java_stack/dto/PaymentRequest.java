package hieunv.dev.java_stack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequest {

    private String userId;
    private Double amount;
    private String idempotencyKey;
}
