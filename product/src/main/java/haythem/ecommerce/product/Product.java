package haythem.ecommerce.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
@Entity
public class Product {
    @Id @GeneratedValue
    Integer id;
}
