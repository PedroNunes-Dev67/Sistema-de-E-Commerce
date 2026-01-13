package E_Commerce_Spring.service;

import E_Commerce_Spring.dto.OrderDtoResponse;
import E_Commerce_Spring.exception.ResourceNotFoundException;
import E_Commerce_Spring.model.Order;
import E_Commerce_Spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

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
}
