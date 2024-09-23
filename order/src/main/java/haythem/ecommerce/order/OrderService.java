package haythem.ecommerce.order;

import haythem.ecommerce.customer.CustomerClient;
import haythem.ecommerce.exception.BusinessException;
import haythem.ecommerce.orderline.OrderLineRequest;
import haythem.ecommerce.orderline.OrderLineService;
import haythem.ecommerce.product.ProductClient;
import haythem.ecommerce.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;


    public Integer createOrder(OrderRequest orderRequest) {
        //check the customer --> Open feign
        var customer = customerClient.findCustomerById(orderRequest.customerId()).orElseThrow(
                () -> new BusinessException("Cannot Create Order :: Not Customer exist with the provided id")
        );
        //purchase the product --> Product Microservice (RestTemplate)
        var products = this.productClient.purchaseProducts(orderRequest.products());

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
        return null;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public OrderResponse getOrder(Integer id) {
        return null;
    }
}
