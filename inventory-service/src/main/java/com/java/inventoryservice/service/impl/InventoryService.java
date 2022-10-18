package com.java.inventoryservice.service.impl;

import com.java.inventoryservice.dto.InventoryDto;
import com.java.inventoryservice.dto.InventoryRequest;
import com.java.inventoryservice.dto.InventoryResponse;
import com.java.inventoryservice.exception.InventoryNotFoundException;
import com.java.inventoryservice.model.Inventory;
import com.java.inventoryservice.repository.InventoryRepository;
import com.java.inventoryservice.service.IInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class InventoryService implements IInventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public boolean isInstock(String skuCode) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(() -> new InventoryNotFoundException("Inventory Not Found !!!"));
        return inventory.getQuantity() == 0;
    }

    @Transactional(rollbackOn = {Exception.class})
    public InventoryResponse addInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = mapToInventory(inventoryRequest);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return mapToInventoryResponse(savedInventory);
    }

    public Inventory mapToInventory(InventoryRequest inventoryRequest) {
        return Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
    }

    public InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .build();
    }
}
