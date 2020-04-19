package cz.ucl.controller;

import cz.ucl.model.shopOrder.ShopOrder;
import cz.ucl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/order/{id}")
    public ResponseEntity<Object> getOrder(HttpEntity<String> httpEntity, @PathVariable Integer id){
        Optional<ShopOrder> order = orderService.getOrderById(id);
        if (order.isEmpty()) {
            return ResponseEntity.status(400).body("Order does not exist. Wrong ID.");
        }
        return ResponseEntity.status(200).body(order.get());
    }

    @GetMapping(value = "/order/orderByDate")
    public List<ShopOrder> ordersByDate() {
        return orderService.orderByCompletedAt();
    }

}
