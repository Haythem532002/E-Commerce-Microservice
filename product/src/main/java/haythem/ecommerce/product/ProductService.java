package haythem.ecommerce.product;

import haythem.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {
        return repository.save(mapper.toProduct(request)).getId();
    }


    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request
    ) {
        //get list of all product ids
        List<Integer> productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList()
                ;
        // check if all the products exist in the database
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size()!=storedProducts.size()) {
            throw new ProductPurchaseException("One Or more products does not exists");
        }
        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList()
                ;

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i=0;i<storedProducts.size();i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity");
            }
            double newQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newQuantity);
            repository.save(product);
            purchasedProducts.add(
              mapper.toProductPurchaseResponse(product,productRequest.quantity())
            );
        }
        return purchasedProducts;
    }

    public List<ProductResponse> getProducts() {
        return repository.
                findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList())
                ;
    }

    public ProductResponse getProduct(Integer id) {
        return repository.findById(id)
                .map(mapper::toProductResponse)
                .orElseThrow(
                        ()->new EntityNotFoundException("Product not found")
                );
    }

    public void updateProduct(ProductRequest request) {
        var product = repository.findById(request.id()).orElse(null);
        if (product == null) return;
        if (StringUtils.isNotBlank(request.name())) {
            product.setName(request.name());
        }
        if (StringUtils.isNotBlank(request.description())) {
            product.setDescription(request.description());
        }
        if (request.price() != null) {
            product.setPrice(request.price());
        }
        repository.save(product);
    }

    public void deleteProduct(Integer productId) {
        repository.deleteById(productId);
    }


}
