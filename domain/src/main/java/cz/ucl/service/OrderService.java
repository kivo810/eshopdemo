package cz.ucl.service;

import cz.ucl.dao.OrderRepository;
import cz.ucl.dao.ProductRepository;
import cz.ucl.model.product.Product;
import cz.ucl.model.shopOrder.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @PostConstruct
    public void initOrders(){
        ShopOrder aa = new ShopOrder();
        aa.setCompleted(false);
        aa.setCompletedAt(LocalDateTime.now());
        List<Product> qq = new ArrayList();
        qq.add(productService.getProduct(1));
        qq.add(productService.getProduct(3));
        aa.setProductList(qq);
        orderRepository.save(aa);
    }

    public void addProductsToOrderString(){

    }

}
