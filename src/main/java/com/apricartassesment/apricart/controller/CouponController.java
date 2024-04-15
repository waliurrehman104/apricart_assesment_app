package com.apricartassesment.apricart.controller;

import com.apricartassesment.apricart.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/validate/{couponCode}")
    public ResponseEntity<Boolean> validateCoupon(@PathVariable String couponCode, @RequestParam double orderTotal) {
        boolean isValid = couponService.validateCoupon(couponCode, orderTotal);
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/apply/{couponCode}")
    public ResponseEntity<Double> applyCoupon(@PathVariable String couponCode, @RequestParam double orderTotal) {
        double discountedTotal = couponService.applyCouponDiscount(couponCode, orderTotal);
        return ResponseEntity.ok(discountedTotal);
    }
}
