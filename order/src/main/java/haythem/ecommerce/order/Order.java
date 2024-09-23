package haythem.ecommerce.order;

import haythem.ecommerce.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_order")
@Entity
public class Order {
    @Id
    @GeneratedValue
    Integer id;
    String reference;
    BigDecimal totalAmount;
    @Enumerated(STRING)
    PaymentMethod paymentMethod;
    String customerId;
    @OneToMany(mappedBy = "order")
    List<OrderLine> orderLines;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    LocalDateTime lastModifiedDate;
}
