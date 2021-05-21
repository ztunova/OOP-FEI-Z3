package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService{
    private CartRepository cartRepository;


    @Override
    public Cart createNew() {
        return new Cart();
    }
}
