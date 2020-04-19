package cz.ucl.controller;

import cz.ucl.model.product.Product;
import cz.ucl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
        if(product.getName() == null || product.getName().equals("")){
            return ResponseEntity.status(400).body("Name is empty or null!");
        }

        if (product.getPrice() > 100000.00 || product.getPrice() < 0) {
            return ResponseEntity.status(400).body("Price is below 0 or higher than 100000");
        }

        if (product.getAvailable() < 0){
            return ResponseEntity.status(400).body("There must be at least one product");
        }

        productService.addProduct(product);
        return ResponseEntity.status(200).body(product);
    }



    @DeleteMapping(value = "/products/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }

}
