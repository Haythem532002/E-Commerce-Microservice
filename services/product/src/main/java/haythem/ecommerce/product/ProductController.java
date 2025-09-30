package haythem.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ) {
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable("product-id") Integer id
    ) {
        return ResponseEntity.ok(service.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(service.getProducts());
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(
            @RequestBody ProductRequest request
    ) {
        service.updateProduct(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/delete/{product-id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("product-id") Integer id
    ) {
        service.deleteProduct(id);
        return ResponseEntity.accepted().build();
    }
}
