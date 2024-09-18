package haythem.ecommerce.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import haythem.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Product {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String description;
    double availableQuantity;
    BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    Category category;
}
