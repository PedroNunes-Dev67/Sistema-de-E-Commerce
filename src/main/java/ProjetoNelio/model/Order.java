package ProjetoNelio.model;

import ProjetoNelio.dto.UserDtoResponse;
import ProjetoNelio.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1")
    private Long id;
    @Schema(example = "2019-04-03T03:38:21Z")
    private Instant dataHora;

    @Schema(example = "WAITING_PAYMENT")
    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order() {
    }

    public Order(Long id, Instant dataHora,OrderStatus orderStatus, User client) {
        this.id = id;
        this.dataHora = dataHora;
        this.client = client;
        setOrderStatus(orderStatus);
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

    public void setDataHora(Instant dataHora) {
        this.dataHora = dataHora;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public UserDtoResponse getClient() {
        return new UserDtoResponse(
                this.client.getId(),
                this.client.getName(),
                this.client.getEmail(),
                this.client.getPhone()
        );
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Double getTotal(){
        return items.stream().mapToDouble(item -> {
           Double sum = 0.0;
           return sum += item.getSubTotal();
        }).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
