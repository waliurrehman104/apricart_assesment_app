package com.apricartassesment.apricart.service;

import com.apricartassesment.apricart.entity.CouponEntity;
import com.apricartassesment.apricart.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponService {

    private static final Logger logger = LoggerFactory.getLogger(CouponService.class);

    @Autowired
    private CouponRepository couponRepository;

    public boolean validateCoupon(String couponCode, double orderTotal) {
        logger.info("Validating coupon with code: {}", couponCode);
        Optional<CouponEntity> optionalCoupon = couponRepository.findByCouponCode(couponCode);
        if (optionalCoupon.isPresent()) {
            CouponEntity coupon = optionalCoupon.get();
            if (coupon.isActive() && orderTotal >= coupon.getMinOrderAmount()) {
                logger.info("Coupon is valid for order total: {}", orderTotal);
                return true;
            }
        }
        logger.info("Coupon validation failed for code: {}", couponCode);
        return false;
    }

    public double applyCouponDiscount(String couponCode, double orderTotal) {
        logger.info("Applying coupon with code: {}", couponCode);
        Optional<CouponEntity> optionalCoupon = couponRepository.findByCouponCode(couponCode);
        if (optionalCoupon.isPresent()) {
            CouponEntity coupon = optionalCoupon.get();
            if (coupon.isActive() && orderTotal >= coupon.getMinOrderAmount()) {
                double discountAmount = coupon.getDiscountAmount();
                if (coupon.isPercentageBased()) {
                    // Percentage-based discount
                    double discount = orderTotal * (discountAmount / 100);
                    logger.info("Applied percentage-based discount: {}", discount);
                    return orderTotal - discount;
                } else {
                    // Fixed amount discount
                    logger.info("Applied fixed amount discount: {}", discountAmount);
                    return orderTotal - discountAmount;
                }
            }
        }
        logger.info("Coupon not applied or not valid for code: {}", couponCode);
        return orderTotal; // Return original order total if coupon is not valid
    }
}
