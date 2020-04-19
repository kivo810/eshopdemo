package cz.ucl.service;

import cz.ucl.dao.OrderRepository;
import cz.ucl.model.shopOrder.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void createOrder(ShopOrder shopOrder){
        orderRepository.save(shopOrder);
    }

    public List<ShopOrder> orderByCompletedAt() {
        return orderRepository.findAllByCompletedAtOrderByCompletedAtAsc();
    }
}
