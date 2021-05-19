package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.products.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @Autowired
    private IProductService service;

    @GetMapping("/product")
    public List<ProductResponse> getAllProducts(){
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest request){
        return new ProductResponse(this.service.create(request));
    }

    @GetMapping("/product/{id}")
    public ProductResponse findProductById(@PathVariable("id") Long id){
        var findedOpt= this.service.findById(id);
        Product findedProduct= findedOpt.get();
        return new ProductResponse(findedProduct);
        /*if(result.isPresent()) {
            return result;
        }
        else{throw new NotFoundException();}*/
    }

    @PutMapping("/product/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest updateRequest){
        return new ProductResponse(service.updateProduct(updateRequest, id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id){
        return this.service.deleteById(id);
    }

    @GetMapping("/product/{id}/amount")
    public ProductAmountResponse productAmount(@PathVariable("id") Long id){
        return new ProductAmountResponse(this.service.findProductAmount(id));
    }

    @PostMapping("/product/{id}/amount")
    public ProductAmountResponse increaseProductAmount(@PathVariable("id") Long id,@RequestBody ProductRequest addAmount){
        return new ProductAmountResponse(this.service.increaseAmount(addAmount, id).getAmount());
    }
}
