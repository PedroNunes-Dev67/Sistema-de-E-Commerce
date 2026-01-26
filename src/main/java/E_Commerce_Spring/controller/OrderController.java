package E_Commerce_Spring.controller;


import E_Commerce_Spring.dto.request.OrderDtoRequest;
import E_Commerce_Spring.dto.response.OrderDtoResponse;
import E_Commerce_Spring.exception.StandardError;
import E_Commerce_Spring.model.Order;
import E_Commerce_Spring.security.SecurityConfiguration;
import E_Commerce_Spring.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Order Controller", description = "Controlador de funções relacionadas a um pedido")
@ApiResponse(responseCode = "403", description = "Usuário não autorizado", content = @Content(mediaType = "application/json"))
@RestController
@RequestMapping("/orders")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Cria um pedido para um usuário")
    @ApiResponse(responseCode = "201",description = "Pedido criado",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = OrderDtoResponse.class)))
    @PostMapping
    public ResponseEntity<OrderDtoResponse> createOrder(){

        OrderDtoResponse order = orderService.createOrder();

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @Operation(summary = "Adiciona items de um pedido de um usuário")
    @ApiResponse(responseCode = "201",description = "Adicionado items ao pedido",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = OrderDtoResponse.class)))
    @ApiResponse(responseCode = "400", description = "Pedido não pertence ao usuário",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = StandardError.class)))
    @ApiResponse(responseCode = "404", description = "Pedido ou produto não encontrado",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = StandardError.class)))
    @PostMapping("/orderitem")
    public ResponseEntity<OrderDtoResponse> createOrderItem(@RequestBody @Valid OrderDtoRequest orderDtoRequest){

        OrderDtoResponse order = orderService.createOrderItem(orderDtoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @Operation(summary = "Pega os pedidos relacionados ao usuário",description = "Paginação de pedidos passando a pagina como paramentro")
    @ApiResponse(responseCode = "200",description = "Pedidos retornados",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = Page.class)))
    @ApiResponse(responseCode = "204",description = "Usuário não possui pedidos")
    @GetMapping
    public ResponseEntity<Page<Order>> findByOrderReferencesClient(@RequestParam int page){

        Page<Order> pageFind = orderService.findByOrderReferencesClient(page);

        if (pageFind == null) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(pageFind);
    }
}
