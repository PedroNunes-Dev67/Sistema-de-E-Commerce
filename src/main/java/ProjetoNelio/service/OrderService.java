package ProjetoNelio.service;

import ProjetoNelio.dto.OrderDtoResponse;
import ProjetoNelio.exception.ResourceNotFoundException;
import ProjetoNelio.model.Order;
import ProjetoNelio.repository.OrderRepository;
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
