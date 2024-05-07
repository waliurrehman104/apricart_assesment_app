package com.apricartassesment.apricart.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apricartassesment.apricart.entity.CartEntity;
import com.apricartassesment.apricart.helpers.CartItemNotFoundException;
import com.apricartassesment.apricart.repository.CartRepository;

@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public CartEntity addToCart(CartEntity cartItem) {
        logger.info("Adding item to cart: {}", cartItem);
        return cartRepository.save(cartItem);
    }

    public CartEntity updateCart(Long cartId, int quantity) {
        logger.info("Updating cart item with ID {} to quantity {}", cartId, quantity);
        CartEntity cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found with id: " + cartId));
        cartItem.setQuantity(quantity);
        return cartRepository.save(cartItem);
    }

    public void removeFromCart(Long cartId) {
        logger.info("Removing item from cart with ID: {}", cartId);
        cartRepository.findById(cartId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found with id: " + cartId));
        cartRepository.deleteById(cartId);
    }

    public void clearCart() {
        logger.info("Clearing cart");
        cartRepository.deleteAll();
    }

    public List<CartEntity> getCartItems(String userId) {
        logger.info("Getting cart items for user ID: {}", userId);
        List<CartEntity> cartItems = cartRepository.findByUserId(userId);
        return cartItems != null ? cartItems : Collections.emptyList();
    }

    public double getCartTotal(String userId) {
        logger.info("Calculating total for user ID: {}", userId);
        List<CartEntity> cartItems = getCartItems(userId);
        double total = 0;
        for (CartEntity item : cartItems) {
            Double productPrice = productService.getProductPrice(item.getProductId());
            if (productPrice != null) {
                total += productPrice * item.getQuantity();
            } else {
                logger.warn("Price not found for product with ID {}", item.getProductId());
            }
        }
        logger.info("Total calculated for user ID {}: {}", userId, total);
        return total;
    }
}
