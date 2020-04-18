package cz.ucl.service;

import cz.ucl.dao.OrderRepository;
import cz.ucl.dao.ProductRepository;
import cz.ucl.model.product.Product;
import cz.ucl.model.shopOrder.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public void initOrders(){
        ShopOrder a = new ShopOrder();
        a.setCompleted(false);
        a.setCompletedAt(LocalDateTime.now());
        orderRepository.save(a);
    }


    public void addProductToOrder(int id){
    }

}
