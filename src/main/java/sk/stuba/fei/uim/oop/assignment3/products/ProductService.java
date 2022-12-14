package sk.stuba.fei.uim.oop.assignment3.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.myExceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repo){
        this.repository= repo;
    }

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product created= new Product();
        created.setName(request.getName());
        created.setDescription(request.getDescription());
        created.setAmount(request.getAmount());
        created.setUnit(request.getUnit());
        created.setPrice(request.getPrice());
        return repository.save(created);
    }

    @Override
    public Optional<Product> findById(Long findingId) {
        var result= this.repository.findById(findingId);
        if(result.isPresent()) {
            return result;
        }
        else{throw new NotFoundException();}
    }

    @Override
    public Product updateProduct(ProductRequest updateRequest, Long id) {
        Optional<Product> finded= findById(id);
        Product findedProduct= finded.get();
        if(updateRequest.getName() != null){
            findedProduct.setName(updateRequest.getName());
        }
        if(updateRequest.getDescription() != null){
            findedProduct.setDescription(updateRequest.getDescription());
        }

        return repository.save(findedProduct);
    }

    @Override
    public ResponseEntity deleteById(Long id) {
        Optional<Product> finded= findById(id);
        if(finded.isPresent()) {
            Product findedProduct = finded.get();
            this.repository.delete(findedProduct);
            return new ResponseEntity(HttpStatus.OK);
        }
        else{throw new NotFoundException();}
    }

    @Override
    public Integer findProductAmount(Long id) {
        Optional<Product> finded= findById(id);
        if(finded.isPresent()) {
            Product findedProduct = finded.get();
            return findedProduct.getAmount();
        }
        else{throw new NotFoundException();}
    }

    @Override
    public Product increaseAmount(ProductRequest addAmount, Long id) {
        var opt= findById(id);
        Product result= opt.get();
        int increasedAmount= result.getAmount() + addAmount.getAmount();
        result.setAmount(increasedAmount);
        return this.repository.save(result);
    }

    public void decreaseAmount(int soldAmount, Long id){
        var opt= findById(id);
        Product result= opt.get();
        int decreasedAmount= result.getAmount() - soldAmount;
        result.setAmount(decreasedAmount);
        this.repository.save(result);
    }

}
