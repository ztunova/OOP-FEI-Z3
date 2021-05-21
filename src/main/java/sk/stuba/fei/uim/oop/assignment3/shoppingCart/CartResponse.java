package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartResponse {
    private Long id;
    private List<ShoppingListResponse> shoppingList;
    private boolean payed;

    public CartResponse(Cart cart){
        this.id= cart.getCartId();
        this.shoppingList = new ArrayList<>();
        for (ShoppingListItem si : cart.getShoppingList()){
            ShoppingListResponse slr = new ShoppingListResponse();
            slr.setAmount(si.getAmount());
            slr.setProductId(si.getProductId());
            this.shoppingList.add(slr);
        }

        this.payed= cart.isPayed();
    }
}
