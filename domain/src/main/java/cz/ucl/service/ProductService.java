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
//        Product newOne = new Product();
//        newOne.setId(989549);
//        newOne.setName("Socks");
//        newOne.setDescription("old");
//        newOne.setPrice(12.00);
//        newOne.setAvailable(15);
//        productRepository.save(newOne);
//        Product aa = new Product();
//        aa.setName("ere");
//        aa.setPrice(1.00);
//        aa.setAvailable(1000);
//        productRepository.save(aa);
//        Product rr = new Product();
//        rr.setName("boots");
//        rr.setDescription("new");
//        rr.setPrice(101.10);
//        rr.setAvailable(22);
//        Product adding = new Product("red","one",12.70,15);
//        productRepository.save(adding);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
