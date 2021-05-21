package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/cart/{id}")
    private CartResponse findCartById(@PathVariable("id") Long id){
        var result= this.cartService.findById(id);
        return new CartResponse(result);
    }
}
