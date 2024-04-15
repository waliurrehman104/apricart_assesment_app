package com.apricartassesment.apricart.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apricartassesment.apricart.entity.WarehouseEntity;
import com.apricartassesment.apricart.repository.WarehouseRepository;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public WarehouseEntity createWarehouse(WarehouseEntity warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public WarehouseEntity updateWarehouse(Long warehouseId, WarehouseEntity warehouseDetails) {
        WarehouseEntity warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + warehouseId));

        warehouse.setWarehouseName(warehouseDetails.getWarehouseName());
        warehouse.setLocation(warehouseDetails.getLocation());
        warehouse.setCapacity(warehouseDetails.getCapacity());
        warehouse.setAvailableCapacity(warehouseDetails.getAvailableCapacity());

        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long warehouseId) {
        warehouseRepository.deleteById(warehouseId);
    }

    public Optional<WarehouseEntity> getWarehouseById(Long warehouseId) {
        return warehouseRepository.findById(warehouseId);
    }

    public List<WarehouseEntity> getAllWarehouses() {
        return warehouseRepository.findAll();
    }
}

