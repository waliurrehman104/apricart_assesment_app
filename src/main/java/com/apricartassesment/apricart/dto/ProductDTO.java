package com.apricartassesment.apricart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long productId;
    private String productName;
    private String description;
    private double price;
    private String brand;
    private boolean status;
    private Long warehouseId;
}
