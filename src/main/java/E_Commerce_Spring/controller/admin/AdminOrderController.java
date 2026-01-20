package E_Commerce_Spring.controller.admin;

import E_Commerce_Spring.dto.response.OrderDtoResponse;
import E_Commerce_Spring.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Admin Orders Controller", description = "Controle das funções de pedidos pelo admin")
@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDtoResponse>> findAll(){

        List<OrderDtoResponse> list = orderService.findAll();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> findById(@PathVariable Long id){

        OrderDtoResponse order = orderService.findById(id);

        return ResponseEntity.ok(order);
    }
}
