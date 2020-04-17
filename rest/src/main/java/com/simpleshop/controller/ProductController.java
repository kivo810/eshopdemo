package com.simpleshop.controller;

import com.simpleshop.model.product.Product;
import com.simpleshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }
}
