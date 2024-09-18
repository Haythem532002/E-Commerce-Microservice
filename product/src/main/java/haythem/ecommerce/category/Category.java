package haythem.ecommerce.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import haythem.ecommerce.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Category {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String description;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    List<Product> products;
}
