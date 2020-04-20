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

    public List<Product> getCart(){
        return cart;
    }

    public void addProductToCart(Product product){
        cart.add(product);
    }

    public void deleteProductFromCartViaId(int id){
        cart.remove(id);
    }

//    public void deleteProductFromCart(Product product){
//        cart.remove(product);
//    }

    public void removeCart(){
        cart = new ArrayList<>();
    }

    public Double calculateFinalPrice(){
        Double finalPrice = 0.00;

        for (Product product : cart) {
            finalPrice += product.getPrice();
        }
        return finalPrice;
    }

    //TODO -- complete + logic
    public int calculatAvailable(){
        return 0;
    }
}
