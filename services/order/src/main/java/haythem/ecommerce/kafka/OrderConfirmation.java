package haythem.ecommerce.kafka;

import haythem.ecommerce.customer.CustomerResponse;
import haythem.ecommerce.order.PaymentMethod;
import haythem.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
