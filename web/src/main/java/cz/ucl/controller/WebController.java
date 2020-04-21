package cz.ucl.controller;

import cz.ucl.model.product.Product;
import cz.ucl.model.shopOrder.ShopOrder;
import cz.ucl.service.CartService;
import cz.ucl.service.OrderService;
import cz.ucl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;


    @GetMapping(value = "/")
    public String index(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/cart")
    public String cart(Model model){
        List<Product> cart = cartService.getCart();
        model.addAttribute("cart", cart);

        Double finalPrice = cartService.calculateFinalPrice();
        model.addAttribute("finalPrice", finalPrice);

        return "cart";
    }

    //DONE
    @PostMapping(value = "/add")
    public ResponseEntity<Object> addProductToCart(@RequestParam Map<String, String> input){
        if (productService.getProductFromId(Integer.parseInt(input.get("productId"))).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product does not exist. Wrong ID.");
        }
        Optional<Product> product = productService.getProductFromId(Integer.parseInt(input.get("productId")));
        cartService.addProductToCart(product.get());
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    //DONE
    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Object> removeProductFromCart(@PathVariable int id) {
        cartService.deleteProductFromCartViaId(id);

        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders());
    }

    //TODO -- complete order, write to DB
    @PostMapping(value = "/order")
    public ResponseEntity<Object> completeOrder(@RequestParam Map<String, String> input) {
//        Double finalPrice = Double.valueOf(input.get("finalPrice"));

//        ShopOrder order = new ShopOrder();
//        List<Product> orderedProduct = cartService.getCart();
//        order.setProductList(orderedProduct);
//        order.setFinalPrice(14.47);
//        order.setCompletedAt(new Timestamp(System.currentTimeMillis()));
//        order.setCustomerId(2);
//
//        //CUSTOMER
//        order.setName(input.get("name"));
//        order.setAddress(input.get("address"));
//        order.setEmail(input.get("email"));
//        order.setCardNumber(input.get("creditCardNumber"));
//        order.setAge(Integer.parseInt(input.get("age")));

        ShopOrder order = new ShopOrder();
        order.setProductList(productService.getAllProducts());
        order.setFinalPrice(14.47);
        order.setCompletedAt(new Timestamp(System.currentTimeMillis()));
//        order.setCustomerId(2);

        //CUSTOMER
        order.setName("rei");
        order.setAddress("copa");
        order.setEmail(input.get("aa@ew.ew"));
        order.setCardNumber("4111111111111111");
        order.setAge(55);

        orderService.createOrder(order);
        cartService.removeCart();

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping(value = "/succesfulOrder")
    public String succesfulOrder (Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);

        return "succesfulOrder";
    }
}
