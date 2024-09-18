package haythem.ecommerce.product;

import haythem.ecommerce.category.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product Name is required")
        String name,
        @NotNull(message = "Product Description is required")
        String description,
        @Positive(message = "Product quantity need to be positive")
        double availableQuantity,
        @Positive(message = "Product Price is positive")
        BigDecimal price,
        @NotNull(message = "Product Category is required")
        Integer categoryId
) {
}
