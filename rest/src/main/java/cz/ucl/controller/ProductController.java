package cz.ucl.controller;

import cz.ucl.model.product.Product;
import cz.ucl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping(value = "/products/{id}")
    @ResponseBody
    public ResponseEntity<Object> getProduct(HttpEntity<String> httpEntity, @PathVariable Integer id) {
        if (productService.getProductFromId(id).isEmpty()){
            return ResponseEntity.status(400).body("No product with this ID.");
        }
        else {
            return ResponseEntity.status(200).body(productService.getProductFromId(id));
        }
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

    @PostMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable int id){
        //Validations
        if (productService.getProductFromId(id).isEmpty()){
            return ResponseEntity.status(400).body("Product with this ID does not exist");
        }
        if (product.getPrice() > 100000.00 || product.getPrice() < 0){
            return ResponseEntity.status(400).body("Price is below 0 or higher than 100000");
        }
        if (product.getAvailable() < 0){
            return ResponseEntity.status(400).body("There must be at least one product");
        }

        Optional<Product> optionalProduct = productService.getProductFromId(id);
        Product newProduct = optionalProduct.get();

        newProduct.setPrice(product.getPrice());

        if (product.getName() != null && !product.getName().equals("")) {
            newProduct.setName(product.getName());
        }
        if (product.getDescription() != null && !product.getDescription().equals("")){
            newProduct.setDescription(product.getDescription());
        }
        newProduct.setAvailable(product.getAvailable());

        Product toShow = productService.addProduct(product);
        return ResponseEntity.status(200).body(toShow);
    }


    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Object> deleteProduct(HttpEntity<String> httpEntity, @PathVariable Integer id){
        if (productService.getProductFromId(id).isEmpty()){
            return ResponseEntity.status(400).body("Product with this ID does not exist");
        }
        else {
            productService.deleteProduct(id);
            return ResponseEntity.status(200).build();
        }
    }

}
