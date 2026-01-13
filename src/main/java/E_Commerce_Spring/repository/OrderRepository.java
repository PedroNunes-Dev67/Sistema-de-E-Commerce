package E_Commerce_Spring.repository;

import E_Commerce_Spring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
