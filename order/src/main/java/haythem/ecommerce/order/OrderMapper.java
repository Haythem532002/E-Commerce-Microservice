package haythem.ecommerce.order;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        return Order
                .builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .paymentMethod(request.paymentMethod())
                .totalAmount(request.amount())
                .build()
                ;
    }
}
