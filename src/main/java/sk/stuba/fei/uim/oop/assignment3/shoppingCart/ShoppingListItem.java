package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ShoppingListItem {
    private Long productId;
    private int amount;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
}
