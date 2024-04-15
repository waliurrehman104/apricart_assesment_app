package com.apricartassesment.apricart.controller;

import com.apricartassesment.apricart.entity.WarehouseEntity;
import com.apricartassesment.apricart.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

	@Autowired
	private WarehouseService warehouseService;

	@PostMapping("/create")
	public ResponseEntity<WarehouseEntity> createWarehouse(@RequestBody WarehouseEntity warehouse) {
		WarehouseEntity createdWarehouse = warehouseService.createWarehouse(warehouse);
		return new ResponseEntity<>(createdWarehouse, HttpStatus.CREATED);
	}

	@PutMapping("/update/{warehouseId}")
	public ResponseEntity<WarehouseEntity> updateWarehouse(@PathVariable Long warehouseId,
			@RequestBody WarehouseEntity warehouseDetails) {
		WarehouseEntity updatedWarehouse = warehouseService.updateWarehouse(warehouseId, warehouseDetails);
		return ResponseEntity.ok(updatedWarehouse);
	}

	@DeleteMapping("/delete/{warehouseId}")
	public ResponseEntity<Void> deleteWarehouse(@PathVariable Long warehouseId) {
		warehouseService.deleteWarehouse(warehouseId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{warehouseId}")
	public ResponseEntity<WarehouseEntity> getWarehouseById(@PathVariable Long warehouseId) {
		Optional<WarehouseEntity> warehouse = warehouseService.getWarehouseById(warehouseId);
		return warehouse.map(value -> ResponseEntity.ok().body(value))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/all")
	public ResponseEntity<List<WarehouseEntity>> getAllWarehouses() {
		List<WarehouseEntity> warehouses = warehouseService.getAllWarehouses();
		return ResponseEntity.ok(warehouses);
	}
}
