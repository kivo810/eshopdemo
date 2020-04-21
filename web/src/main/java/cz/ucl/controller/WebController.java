package cz.ucl.controller;

import cz.ucl.model.product.Product;
import cz.ucl.service.CartService;
import cz.ucl.service.OrderService;
import cz.ucl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

//    @GetMapping(value = "/")
    @RequestMapping("/")
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

    //TODO -- remove product from cart

    //TODO -- complete order, write to DB

    @GetMapping(value = "/succesfulOrder")
    public String succesfulOrder (Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);

        return "succesfulOrder";
    }
}
