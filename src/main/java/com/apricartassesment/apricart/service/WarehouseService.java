package com.apricartassesment.apricart.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apricartassesment.apricart.entity.WarehouseEntity;
import com.apricartassesment.apricart.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseService.class);

    @Autowired
    private WarehouseRepository warehouseRepository;

    public WarehouseEntity createWarehouse(WarehouseEntity warehouse) {
        logger.info("Creating warehouse: {}", warehouse);
        return warehouseRepository.save(warehouse);
    }

    public WarehouseEntity updateWarehouse(Long warehouseId, WarehouseEntity warehouseDetails) {
        logger.info("Updating warehouse with ID {}: {}", warehouseId, warehouseDetails);
        WarehouseEntity warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + warehouseId));

        warehouse.setWarehouseName(warehouseDetails.getWarehouseName());
        warehouse.setLocation(warehouseDetails.getLocation());
        warehouse.setCapacity(warehouseDetails.getCapacity());
        warehouse.setAvailableCapacity(warehouseDetails.getAvailableCapacity());

        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long warehouseId) {
        logger.info("Deleting warehouse with ID {}", warehouseId);
        warehouseRepository.deleteById(warehouseId);
    }

    public Optional<WarehouseEntity> getWarehouseById(Long warehouseId) {
        logger.info("Fetching warehouse with ID {}", warehouseId);
        return warehouseRepository.findById(warehouseId);
    }

    public List<WarehouseEntity> getAllWarehouses() {
        logger.info("Fetching all warehouses");
        return warehouseRepository.findAll();
    }
}
