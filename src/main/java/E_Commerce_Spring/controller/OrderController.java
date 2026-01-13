package E_Commerce_Spring.controller;


import E_Commerce_Spring.dto.OrderDtoResponse;
import E_Commerce_Spring.exception.StandardError;
import E_Commerce_Spring.model.Order;
import E_Commerce_Spring.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Busca uma lista de dados de todas os pedidos")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos buscada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class)))
    @GetMapping
    public ResponseEntity<List<OrderDtoResponse>> findAll(){

        List<OrderDtoResponse> list = orderService.findAll();

        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Busca os dados de um pedido pelo id passado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> findById(@PathVariable Long id){

        OrderDtoResponse order = orderService.findById(id);

        return ResponseEntity.ok(order);
    }
}
