package haythem.ecommerce.customer;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document
public class Customer {
    @Id
    String id;
    String firstname;
    String lastname;
    String email;
    Address address;
}
