package sk.stuba.fei.uim.oop.assignment3.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAmountResponse {
    private int amount;

    public ProductAmountResponse(int a){
        this.amount= a;
    }

    public ProductAmountResponse(){}
}
