package ProjetoNelio.controller;


import ProjetoNelio.model.Order;
import ProjetoNelio.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Order Controller", description = "Controlador de funções relacionadas a um pedido")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = orderService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id){
        return orderService.findById(id);
    }
}
