package com.example.cart;

import com.example.product.Product;
import com.example.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class CartService {
    private final List<CartItem> items = new ArrayList<>();
    
    @Autowired
    private ProductService productService;
    
    public void addItem(Long productId, int quantity) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();
            
            if (existingItem.isPresent()) {
                existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
            } else {
                items.add(new CartItem(product, quantity));
            }
        }
    }
    
    public void updateQuantity(Long productId, int quantity) {
        if (quantity <= 0) {
            removeItem(productId);
            return;
        }
        
        items.stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .ifPresent(item -> item.setQuantity(quantity));
    }
    
    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }
    
    public List<CartItem> getItems() {
        return items;
    }
    
    public double getTotal() {
        return items.stream()
            .mapToDouble(CartItem::getSubtotal)
            .sum();
    }
    
    public void clear() {
        items.clear();
    }
}
