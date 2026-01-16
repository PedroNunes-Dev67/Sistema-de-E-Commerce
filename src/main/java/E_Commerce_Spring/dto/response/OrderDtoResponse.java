package E_Commerce_Spring.dto.response;

import E_Commerce_Spring.model.Order;
import E_Commerce_Spring.model.OrderItem;
import E_Commerce_Spring.model.Payment;
import E_Commerce_Spring.model.User;
import E_Commerce_Spring.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Set;

public class OrderDtoResponse {

    private Long id;
    private Instant dataHora;
    private OrderStatus orderStatus;
    private UserDtoResponse client;
    private Set<OrderItem> items;
    private Payment payment;

    public OrderDtoResponse(Order order){

        this.id = order.getId();
        this.dataHora = order.getDataHora();
        this.orderStatus = order.getOrderStatus();
        setClient(order.getClient());
        this.items = order.getItems();
        this.payment = order.getPayment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDataHora() {
        return dataHora;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public UserDtoResponse getClient() {
        return client;
    }

    public void setClient(User client) {

        this.client = new UserDtoResponse(client.getId(), client.getName(), client.getEmail(), client.getPhone());
    }

    public Double getTotal(){
        return items.stream().mapToDouble(item -> {
            Double sum = 0.0;
            return sum += item.getSubTotal();
        }).sum();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
