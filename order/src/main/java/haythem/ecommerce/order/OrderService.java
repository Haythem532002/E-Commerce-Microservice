package haythem.ecommerce.order;

import haythem.ecommerce.customer.CustomerClient;
import haythem.ecommerce.exception.BusinessException;
import haythem.ecommerce.kafka.OrderConfirmation;
import haythem.ecommerce.kafka.OrderProducer;
import haythem.ecommerce.orderline.OrderLineRequest;
import haythem.ecommerce.orderline.OrderLineService;
import haythem.ecommerce.product.ProductClient;
import haythem.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public Integer createOrder(OrderRequest orderRequest) {
        //check the customer --> Open feign
        var customer = customerClient.findCustomerById(orderRequest.customerId()).orElseThrow(
                () -> new BusinessException("Cannot Create Order :: No Customer exist with the provided id")
        );
        //purchase the product --> Product Microservice (RestTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(orderRequest.products());

        //persist the order

        var order = orderRepository.save(orderMapper.toOrder(orderRequest));

        //persist the order line
        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //start payment process

        //send the order confirmation --> Notification Microservice using (Kafka)
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.reference(),
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                customer,
                purchasedProducts
        ));
        return order.getId();
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream().map(orderMapper::toOrderResponse)
                .collect(Collectors.toList())
                ;
    }


    public OrderResponse getOrder(Integer id) {
        return orderRepository.findById(id)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Order Found with this order id"));
    }
}
