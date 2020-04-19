package cz.ucl.service;

import cz.ucl.model.product.Product;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private List<Product> cart;

    @PostConstruct
    public void initCart(){
        cart = new ArrayList<>();
    }
}
