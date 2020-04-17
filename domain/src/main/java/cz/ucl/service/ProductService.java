package cz.ucl.service;

import cz.ucl.dao.ProductRepository;
import cz.ucl.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public void initProducts(){
        productRepository.saveAll(Stream.of
                (new Product(1,"socks","red", 12.20,15),
                        new Product(2,"shoes","blue",15.00,16))
                .collect(Collectors.toList()));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
