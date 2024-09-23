package haythem.ecommerce.product;

import java.math.BigDecimal;

public record purchaseResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
