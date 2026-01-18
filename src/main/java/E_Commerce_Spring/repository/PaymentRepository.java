package E_Commerce_Spring.repository;

import E_Commerce_Spring.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
