package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.NotFoundException;

import java.util.Optional;

@Service
public class CartService implements ICartService{
    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createNew() {
        return this.cartRepository.save(new Cart());
    }

    @Override
    public Cart findById(Long id) {
        Optional<Cart> result= this.cartRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new NotFoundException();
        }
    }
}
