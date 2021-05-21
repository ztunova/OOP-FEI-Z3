package sk.stuba.fei.uim.oop.assignment3.shoppingCart;

public interface ICartService {
    Cart createNew();
    Cart findById(Long id);
}
