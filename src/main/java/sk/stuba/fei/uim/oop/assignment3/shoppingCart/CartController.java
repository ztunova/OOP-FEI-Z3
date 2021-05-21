package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping("/cart")
    private ResponseEntity<CartResponse> createNewCart(){
        return new ResponseEntity<>(new CartResponse(cartService.createNew()), HttpStatus.CREATED);
    }
}
