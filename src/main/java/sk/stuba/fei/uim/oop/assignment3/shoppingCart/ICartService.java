package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.http.ResponseEntity;
import sk.stuba.fei.uim.oop.assignment3.products.ProductRequest;

public interface ICartService {
    Cart createNew();
    Cart findById(Long id);
    ResponseEntity deleteById(Long id);
    Cart addProductToShoppingList(ProductToCartRequest productRequest, Long cartId);
    String sumOfCart(Long id);
}
