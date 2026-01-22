package E_Commerce_Spring.controller.admin;

import E_Commerce_Spring.dto.response.OrderDtoResponse;
import E_Commerce_Spring.security.SecurityConfiguration;
import E_Commerce_Spring.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Retorna todos os pedidos do sistema")
    @ApiResponse(responseCode = "200",description = "Pedidos retornado")
    @GetMapping
    public ResponseEntity<List<OrderDtoResponse>> findAll(){

        List<OrderDtoResponse> list = orderService.findAll();

        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Retorna os dados de um pedido pelo id")
    @ApiResponse(responseCode = "200",description = "Pedido retornado")
    @ApiResponse(responseCode = "404",description = "Pedido não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> findById(@PathVariable Long id){

        OrderDtoResponse order = orderService.findById(id);

        return ResponseEntity.ok(order);
    }
}
