package com.java.inventoryservice.controller;

import com.java.inventoryservice.dto.ApiResponse;
import com.java.inventoryservice.dto.InventoryRequest;
import com.java.inventoryservice.service.impl.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<Boolean> isInstock(@PathVariable String skuCode) {
        boolean isInstock = inventoryService.isInstock(skuCode);
        return ResponseEntity.ok(isInstock);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addInventory(@RequestBody InventoryRequest inventoryRequest) {
        ApiResponse response = ApiResponse.builder().build();
        inventoryService.addInventory(inventoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
