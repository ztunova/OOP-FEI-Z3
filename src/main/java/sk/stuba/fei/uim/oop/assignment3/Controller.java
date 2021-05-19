package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sk.stuba.fei.uim.oop.assignment3.products.IProductService;
import sk.stuba.fei.uim.oop.assignment3.products.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.products.ProductResponse;

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
    public ProductResponse createProduct(@RequestBody ProductRequest request){
        return new ProductResponse(this.service.create(request));
    }
}
