package com.java.inventoryservice.controller;

import com.java.inventoryservice.dto.ApiResponse;
import com.java.inventoryservice.dto.InventoryRequest;
import com.java.inventoryservice.dto.InventoryResponse;
import com.java.inventoryservice.entity.Inventory;
import com.java.inventoryservice.service.impl.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> isInstock(@RequestParam(name = "skuCode") List<String> skuCodes) {
        List<InventoryResponse> inventoryResponses = inventoryService.findInventoryBySkucodeIn(skuCodes);
        return ResponseEntity.status(HttpStatus.OK).body(inventoryResponses);
    }

    @PostMapping("/add")
    public ResponseEntity<InventoryResponse> addInventory(@RequestBody InventoryRequest inventoryRequest) {
        InventoryResponse response = inventoryService.addNewInventory(inventoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add-list")
    public ResponseEntity<List<InventoryResponse>> addInventoryList(@RequestBody List<InventoryRequest> inventoriesRequest) {
        List<InventoryResponse> response = inventoryService.addInventoryList(inventoriesRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
