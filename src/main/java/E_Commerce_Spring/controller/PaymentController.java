package E_Commerce_Spring.controller;

import E_Commerce_Spring.dto.response.OrderDtoResponse;
import E_Commerce_Spring.exception.StandardError;
import E_Commerce_Spring.security.SecurityConfiguration;
import E_Commerce_Spring.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Payment Controller", description = "Controle das funções de um pagamento")
@ApiResponse(responseCode = "403", description = "Usuário não autorizado", content = @Content(mediaType = "application/json"))
@RestController
@RequestMapping("/payments/order")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Realiza o pagamento de um pedido")
    @ApiResponse(responseCode = "200", description = "Pagemento realizado",
        content = @Content(mediaType = "application/json",schema = @Schema(implementation = OrderDtoResponse.class)))
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = StandardError.class)))
    @PostMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> paymentOrder(@PathVariable Long id){

        OrderDtoResponse order = paymentService.paymentOrder(id);

        return ResponseEntity.ok(order);
    }
}
