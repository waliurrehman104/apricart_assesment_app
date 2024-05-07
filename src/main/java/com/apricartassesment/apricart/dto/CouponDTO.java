package com.apricartassesment.apricart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponDTO {
    private Long couponId;
    private String couponCode;
    private double discountAmount;
    private double minOrderAmount;
    private String city;
    private Long warehouseId;
    private boolean isActive;
    private boolean percentageBased;
}
