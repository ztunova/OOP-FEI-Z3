package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
