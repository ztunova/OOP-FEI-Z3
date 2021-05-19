package sk.stuba.fei.uim.oop.assignment3.products;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Optional<Product> findById(Long findingId);
}
