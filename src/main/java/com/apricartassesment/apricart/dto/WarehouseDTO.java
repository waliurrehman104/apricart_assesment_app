package com.apricartassesment.apricart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WarehouseDTO {
    private Long warehouseId;
    private String warehouseName;
    private String location;
    private int capacity;
    private int availableCapacity;
}
