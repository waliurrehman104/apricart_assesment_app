package com.apricartassesment.apricart.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {
    private Long cartId;
    private String userId;
    private Long productId;
    private int quantity;
    private Long warehouseId;
}
