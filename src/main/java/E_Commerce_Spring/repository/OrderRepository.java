package E_Commerce_Spring.repository;

import E_Commerce_Spring.model.Order;
import E_Commerce_Spring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Page<Order> findByClient(User client, Pageable pageable);
}
