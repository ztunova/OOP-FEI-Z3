package sk.stuba.fei.uim.oop.assignment3.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private int amount;
    private String unit;
    private double price;

    public ProductResponse(Product p){
        this.id= p.getId();
        this.name= p.getName();
        this.description= p.getDescription();
        this.amount= p.getAmount();
        this.unit= p.getUnit();
        this.price= p.getPrice();
    }
}
