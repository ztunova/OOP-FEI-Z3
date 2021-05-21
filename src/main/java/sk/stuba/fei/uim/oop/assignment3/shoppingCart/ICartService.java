package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.http.ResponseEntity;

public interface ICartService {
    Cart createNew();
    Cart findById(Long id);
    ResponseEntity deleteById(Long id);
}
