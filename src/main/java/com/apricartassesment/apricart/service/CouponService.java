package com.apricartassesment.apricart.service;

import com.apricartassesment.apricart.entity.CouponEntity;
import com.apricartassesment.apricart.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public boolean validateCoupon(String couponCode, double orderTotal) {
        System.out.println("Coupon code received: " + couponCode);

        Optional<CouponEntity> optionalCoupon = couponRepository.findByCouponCode(couponCode);
        if (optionalCoupon.isPresent()) {
            CouponEntity coupon = optionalCoupon.get();
            if (coupon.isActive() && orderTotal >= coupon.getMinOrderAmount()) {
                return true;
            }
        }
        return false;
    }

    public double applyCouponDiscount(String couponCode, double orderTotal) {
        Optional<CouponEntity> optionalCoupon = couponRepository.findByCouponCode(couponCode);
        if (optionalCoupon.isPresent()) {
            CouponEntity coupon = optionalCoupon.get();
            if (coupon.isActive() && orderTotal >= coupon.getMinOrderAmount()) {
                double discountAmount = coupon.getDiscountAmount();
                if (coupon.isPercentageBased()) {
                    // Percentage-based discount
                    return orderTotal * (1 - discountAmount / 100);
                } else {
                    // Fixed amount discount
                    return orderTotal - discountAmount;
                }
            }
        }
        return orderTotal; // Return original order total if coupon is not valid
    }
}
