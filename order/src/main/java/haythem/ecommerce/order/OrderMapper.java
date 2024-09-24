package haythem.ecommerce.order;


import org.springframework.stereotype.Service;

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

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
