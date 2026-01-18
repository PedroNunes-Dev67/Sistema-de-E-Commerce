package E_Commerce_Spring.service;

import E_Commerce_Spring.dto.response.OrderDtoResponse;
import E_Commerce_Spring.exception.ResourceNotFoundException;
import E_Commerce_Spring.model.Order;
import E_Commerce_Spring.model.Payment;
import E_Commerce_Spring.model.User;
import E_Commerce_Spring.model.enums.OrderStatus;
import E_Commerce_Spring.repository.OrderRepository;
import E_Commerce_Spring.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AuthService authService;

    public OrderDtoResponse paymentOrder(Long id){

        User client = authService.getUserAuthenticate();

        Order order = orderRepository.findByIdAndClient(id, client).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));

        Payment payment = new Payment(Instant.now(), order);

        order.setPayment(payment);
        order.setOrderStatus(OrderStatus.PAID);

        paymentRepository.save(payment);
        orderRepository.save(order);

        return new OrderDtoResponse(order);
    }
}
