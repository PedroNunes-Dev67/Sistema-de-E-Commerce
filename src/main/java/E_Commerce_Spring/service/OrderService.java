package E_Commerce_Spring.service;

import E_Commerce_Spring.dto.request.OrderDtoRequest;
import E_Commerce_Spring.dto.response.OrderDtoResponse;
import E_Commerce_Spring.exception.ConflictUserResource;
import E_Commerce_Spring.exception.ResourceNotFoundException;
import E_Commerce_Spring.model.Order;
import E_Commerce_Spring.model.OrderItem;
import E_Commerce_Spring.model.Product;
import E_Commerce_Spring.model.User;
import E_Commerce_Spring.model.enums.OrderStatus;
import E_Commerce_Spring.repository.OrderItemRepository;
import E_Commerce_Spring.repository.OrderRepository;
import E_Commerce_Spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AuthService authService;


    @Transactional(readOnly = true)
    public List<OrderDtoResponse> findAll(){

        return orderRepository.findAll()
                .stream()
                .map(order -> {
                    return new OrderDtoResponse(order);
                }).toList();
    }

    @Transactional(readOnly = true)
    public OrderDtoResponse findById(Long id){

        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        return new OrderDtoResponse(order);
    }

    @Transactional
    public OrderDtoResponse createOrder(){

        User user = authService.getUserAuthenticate();

        Order newOrder = new Order(Instant.now(), OrderStatus.WAITING_PAYMENT, user);

        orderRepository.save(newOrder);

        return new OrderDtoResponse(newOrder);
    }

    public OrderDtoResponse createOrderItem(OrderDtoRequest orderDtoRequest){

        Product product = productRepository.findById(orderDtoRequest.getId_product()).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        Order order = orderRepository.findById(orderDtoRequest.getId_order()).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));

        User user = authService.getUserAuthenticate();

        //Verifico se o pedido está relacionado ao usuário
        if (order.getClient() != user) throw new IllegalArgumentException("Este pedido não pertence ao usuário");

        OrderItem orderItem = new OrderItem(order, product, orderDtoRequest.getQuantity());

        orderItemRepository.save(orderItem);

        return new OrderDtoResponse(order);
    }
}
