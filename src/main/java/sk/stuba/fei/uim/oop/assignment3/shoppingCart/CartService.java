package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.myExceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.myExceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.products.Product;
import sk.stuba.fei.uim.oop.assignment3.products.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.products.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingListItemRepository itemRepository;

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

    @Override
    public ResponseEntity deleteById(Long id) {
        Cart finded= findById(id);
        if(finded != null){
            this.cartRepository.delete(finded);
            return new ResponseEntity(HttpStatus.OK);
        }
        else{throw new NotFoundException();}
    }

    @Override
    public Cart addProductToShoppingList(ProductToCartRequest productRequest, Long cartId) {
        Cart findedCart= findById(cartId);
        if(findedCart.isPayed()){
            throw new BadRequestException();
        }

        Optional<Product> optionalProduct= this.productService.findById(productRequest.getProductId());
        Product product= optionalProduct.get();
        if(product.getAmount() < productRequest.getAmount()){
            throw new BadRequestException();
        }

        boolean productInCart= false;
        ShoppingListItem itemInCart = null;
        for(ShoppingListItem item : findedCart.getShoppingList()){
            if(item.getProductId().equals(product.getId())){
                productInCart = true;
                itemInCart= item;
            }
        }

        if(productInCart){
            int newAmount= itemInCart.getAmount() + productRequest.getAmount();
            itemInCart.setAmount(newAmount);
            this.productService.decreaseAmount(productRequest.getAmount(), productRequest.getProductId());
            return this.cartRepository.save(findedCart);
        }
        else{
            ShoppingListItem newItem = new ShoppingListItem();
            newItem.setProductId(productRequest.getProductId());
            newItem.setAmount(productRequest.getAmount());
            List<ShoppingListItem> shoppingList= findedCart.getShoppingList();
            this.itemRepository.save(newItem);
            shoppingList.add(newItem);
            this.productService.decreaseAmount(productRequest.getAmount(), productRequest.getProductId());
            return this.cartRepository.save(findedCart);
        }
    }

    @Override
    public String sumOfCart(Long id) {
        Cart cart= findById(id);
        if(cart.isPayed()){
            throw new BadRequestException();
        }
        List<ShoppingListItem> shoppingList= cart.getShoppingList();
        Product product;
        double sum= 0.0;
        for(ShoppingListItem item : shoppingList){
            product= this.productService.findById(item.getProductId()).get();
            sum= sum + (product.getPrice() * item.getAmount());
        }
        cart.setPayed(true);
        this.cartRepository.save(cart);
        return Double.toString(sum);
    }


}
