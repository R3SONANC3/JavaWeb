package com.example.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, 
                          @RequestParam(defaultValue = "1") int quantity) {
        cartService.addItem(productId, quantity);
        return "redirect:/cart";
    }
    
    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }
    
    @PostMapping("/update/{productId}")
    public String updateCartItem(@PathVariable Long productId, 
                               @RequestParam int quantity) {
        cartService.updateQuantity(productId, quantity);
        return "redirect:/cart";
    }
    
    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeItem(productId);
        return "redirect:/cart";
    }
    
    @PostMapping("/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart";
    }
}