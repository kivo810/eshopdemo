package cz.ucl.controller;

import cz.ucl.model.product.Product;
import cz.ucl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping(value = "/products/{id}")
    public String getProduct(@PathVariable Integer id) {

        return productService.getProductFromId(id).toString();
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product){
//        product.setName("newname");
//        product.setAvailable(100);
//        product.setDescription("popoospis");
//        product.setPrice(18.98);
//        productService.addProduct(product);
        productService.addProduct(product);
    }

    @DeleteMapping(value = "/products/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }

}
