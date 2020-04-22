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
    @ResponseBody
    public ResponseEntity<Object> addProductToCart(@RequestParam Map<String, String> input){
        if (productService.getProductFromId(Integer.parseInt(input.get("prodId"))).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product does not exist. Wrong ID.");
        }
        Optional<Product> product = productService.getProductFromId(Integer.parseInt(input.get("productId")));
        cartService.addProductToCart(product.get());
        cartService.deleteAvailable(product.get());
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    //DONE
    @PostMapping(value = "/add/{id}")
    @ResponseBody
    public ResponseEntity<Object> addProductToCart(@PathVariable int id){
        if (productService.getProductFromId(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product does not exist. Wrong ID.");
        }
        Optional<Product> product = productService.getProductFromId(id);
        cartService.addProductToCart(product.get());
        cartService.deleteAvailable(product.get());
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    //DONE
    @DeleteMapping(value = "/remove")
    @ResponseBody
    public ResponseEntity<Object> removeProductFromCart(@RequestParam Map<String, String> input) {
        cartService.deleteProductFromCartViaIdx(Integer.parseInt(input.get("prodIndex")));

        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart());
    }

    @DeleteMapping(value = "/remove/{index}")
    @ResponseBody
    public ResponseEntity<Object> removeProductFromCart(@PathVariable int index) {
        cartService.deleteProductFromCartViaIdx(index);

        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart());
    }

    //TODO -- complete order, write to DB
    @PostMapping(value = "/order")
    @ResponseBody
    public ResponseEntity<Object> completeOrder(@RequestParam Map<String, String> input) {
        Double finalPrice = Double.valueOf(input.get("finalPrice"));

        ShopOrder order = new ShopOrder();
        List<Product> orderedProduct = cartService.getCart();
        String orderString = "";
        for (Product product : orderedProduct) {
            orderString = orderString + product.getName() + ", ";
        }
        order.setOrderString(orderString);
//        order.setProductList(orderedProduct);
        order.setFinalPrice(finalPrice);
        order.setCompletedAt(new Timestamp(System.currentTimeMillis()));

        //CUSTOMER
        order.setName(input.get("name"));
        order.setAddress(input.get("address"));
        order.setEmail(input.get("email"));
        order.setCardNumber(input.get("cardNumber"));
        order.setAge(Integer.parseInt(input.get("age")));

        //Hibernate Error
        orderService.createOrder(order);
        cartService.removeCart();

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping(value = "/orderCompleted")
    public String orderCompleted (Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);

        return "orderCompleted";
    }
}
