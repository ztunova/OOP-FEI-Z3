package sk.stuba.fei.uim.oop.assignment3.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repo){
        this.repository= repo;

        Product p1= new Product();
        p1.setName("chleba");
        p1.setDescription("jedlo");
        p1.setAmount(2);
        p1.setUnit("ks");
        p1.setPrice(1.5);
        this.repository.save(p1);
        /*Product p2= new Product();
        p2.setName("muka");
        p2.setDescription("trvanlive");
        p2.setAmount(1);
        p2.setUnit("kg");
        p2.setPrice(1.8);
        this.repository.save(p2);*/
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
       // return this.repository.findById(findingId);
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


}
