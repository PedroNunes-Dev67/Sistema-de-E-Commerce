package E_Commerce_Spring.controller;

import E_Commerce_Spring.dto.response.OrderDtoResponse;
import E_Commerce_Spring.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Payment Controller", description = "Controle das funções de um pagamento")
@RestController
@RequestMapping("/payments/order")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> paymentOrder(@PathVariable Long id){

        OrderDtoResponse order = paymentService.paymentOrder(id);

        return ResponseEntity.ok(order);
    }
}
