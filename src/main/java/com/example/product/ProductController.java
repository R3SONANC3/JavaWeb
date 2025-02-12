package com.example.product;

import com.example.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CartService cartService;  // Add CartService
    
    @GetMapping("/")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("cartItemCount", cartService.getItems().size()); // Add cart count
        return "index";
    }
    
    @GetMapping("/product/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("cartItemCount", cartService.getItems().size());
        return "product-details";
    }
}