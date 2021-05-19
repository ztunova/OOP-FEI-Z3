package sk.stuba.fei.uim.oop.assignment3.products;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Optional<Product> findById(Long findingId);
    Product updateProduct(ProductRequest updateRequest, Long id);
    ResponseEntity deleteById(Long id);
}
