package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/cart/{id}")
    private ResponseEntity deleteCartById(@PathVariable("id") Long id){
        return this.cartService.deleteById(id);
    }

    @PostMapping("/cart/{id}/add")
    private CartResponse addProductToCart(@PathVariable("id") Long id, @RequestBody ProductToCartRequest productRequest){
        return new CartResponse(this.cartService.addProductToShoppingList(productRequest, id));
    }

    @GetMapping("/cart/{id}/pay")
    private String payForCart(@PathVariable("id") Long id){
        return this.cartService.sumOfCart(id);
    }
}
