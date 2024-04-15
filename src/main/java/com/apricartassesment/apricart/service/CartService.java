package com.apricartassesment.apricart.service;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apricartassesment.apricart.entity.CartEntity;
import com.apricartassesment.apricart.helpers.CartItemNotFoundException;
import com.apricartassesment.apricart.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductService productService;

    public CartEntity addToCart(CartEntity cartItem) {
        return cartRepository.save(cartItem);
    }

    public CartEntity updateCart(Long cartId, int quantity) {
        CartEntity cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found with id: " + cartId));
        cartItem.setQuantity(quantity);
        return cartRepository.save(cartItem);
    }

    public void removeFromCart(Long cartId) {
        cartRepository.findById(cartId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found with id: " + cartId));
        cartRepository.deleteById(cartId);
    }

    public void clearCart() {
        cartRepository.deleteAll();
    }

    public List<CartEntity> getCartItems(String userId) {
        List<CartEntity> cartItems = cartRepository.findByUserId(userId);
        return cartItems != null ? cartItems : Collections.emptyList();
    }

    public double getCartTotal(String userId) {
        List<CartEntity> cartItems = getCartItems(userId);
        double total = 0;
        for (CartEntity item : cartItems) {
            Double productPrice = productService.getProductPrice(item.getProductId());
            if (productPrice != null) {
                total += productPrice * item.getQuantity();
            } else {
               continue;
            }
        }
        return total;
    }


}
