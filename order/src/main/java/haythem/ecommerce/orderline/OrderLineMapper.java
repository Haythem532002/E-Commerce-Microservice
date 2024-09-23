package haythem.ecommerce.orderline;

import haythem.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine
                .builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(Order
                        .builder()
                        .id(orderLineRequest.id())
                        .build())
                .productId(orderLineRequest.productId())
                .build();
    }
}
