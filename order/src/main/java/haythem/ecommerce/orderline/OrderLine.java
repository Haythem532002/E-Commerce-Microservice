package haythem.ecommerce.orderline;

import haythem.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
    Integer productId;
    double quantity;
}
