package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartResponse {
    private Long id;
    private List<ShoppingListItem> shoppingList;
    //private List<ShoppingListResponse> shoppingList;
    private boolean payed;

    public CartResponse(Cart cart){
        this.id= cart.getCartId();
        this.shoppingList= cart.getShoppingList();
        this.payed= cart.isPayed();
    }
}
