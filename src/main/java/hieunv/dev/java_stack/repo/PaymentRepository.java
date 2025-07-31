package hieunv.dev.java_stack.repo;

import hieunv.dev.java_stack.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Override
    void flush();

    Payment findByIdempotencyKey(String idempotencyKey);
}