package sk.stuba.fei.uim.oop.assignment3.products;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
}
