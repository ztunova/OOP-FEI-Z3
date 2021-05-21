package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductToCartRequest {
    private Long productId;
    private int amount;
}
