package haythem.ecommerce.notification;

import haythem.ecommerce.kafka.order.OrderConfirmation;
import haythem.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document
public class Notification {
    String id;
    NotificationType notificationType;
    LocalDateTime notificationDate;
    OrderConfirmation orderConfirmation;
    PaymentConfirmation paymentConfirmation;
}
