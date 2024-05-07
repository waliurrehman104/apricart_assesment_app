package com.apricartassesment.apricart.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private Long orderId;
    private Long userId;
    private Long productId;
    private int quantity;
    private double totalPrice;
    private String status;
    private Long warehouseId;
    private List<ProductDTO> products;
}
