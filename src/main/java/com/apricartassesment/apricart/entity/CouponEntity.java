package com.apricartassesment.apricart.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "discount_amount")
    private double discountAmount;

    @Column(name = "min_order_amount")
    private double minOrderAmount;

    @Column(name = "city")
    private String city;

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "is_active")
    private boolean isActive;
    
    @Column(name = "percentage_based")
    private boolean percentageBased; 
}

