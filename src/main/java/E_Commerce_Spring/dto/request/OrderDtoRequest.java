package E_Commerce_Spring.dto.request;

import jakarta.validation.constraints.NotNull;

public class OrderDtoRequest {

    @NotNull(message = "id do produto obrigatório")
    private Long id_product;
    @NotNull(message = "id do pedido obrigatório")
    private Long id_order;
    @NotNull(message = "Quantidade obrigatória")
    private Integer quantity;

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }
}
