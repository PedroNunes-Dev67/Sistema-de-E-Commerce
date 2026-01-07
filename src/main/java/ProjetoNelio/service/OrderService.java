package ProjetoNelio.service;

import ProjetoNelio.dto.OrderDtoResponse;
import ProjetoNelio.exception.ResourceNotFoundException;
import ProjetoNelio.model.Order;
import ProjetoNelio.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDtoResponse> findAll(){
        return orderRepository.findAll()
                .stream()
                .map(order -> {
                    return new OrderDtoResponse(order);
                }).toList();
    }

    public Order findById(Long id){

        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
