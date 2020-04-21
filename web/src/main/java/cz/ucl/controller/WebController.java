package cz.ucl.controller;

import cz.ucl.model.product.Product;
import cz.ucl.service.CartService;
import cz.ucl.service.OrderService;
import cz.ucl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //TODO -- add product to cart
    @PostMapping(value = "/add")
    public ResponseEntity<Object> addProductToCart(@RequestParam Map<String, String> input){
        if (productService.getProductFromId(Integer.valueOf(input.get("productId"))).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product does not exist. Wrong ID.");
        }
        Optional<Product> product = productService.getProductFromId(Integer.valueOf(input.get("productId")));
        cartService.addProductToCart(product.get());
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    //TODO -- remove product from cart
    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Object> removeProductFromCart(@PathVariable int id) {
        cartService.deleteProductFromCartViaId(id);

        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders());
    }

    //TODO -- complete order, write to DB

    @GetMapping(value = "/succesfulOrder")
    public String succesfulOrder (Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);

        return "succesfulOrder";
    }
}
