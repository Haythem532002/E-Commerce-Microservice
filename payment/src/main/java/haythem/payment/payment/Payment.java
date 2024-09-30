package haythem.payment.payment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue
    Integer id;
    BigDecimal amount;
    @Enumerated(STRING)
    PaymentMethod paymentMethod;
    Integer orderId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    LocalDateTime lastModifiedDate;
}
