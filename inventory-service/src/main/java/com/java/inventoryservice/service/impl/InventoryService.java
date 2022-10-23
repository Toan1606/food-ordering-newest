package com.java.inventoryservice.service.impl;

import com.java.inventoryservice.dto.InventoryRequest;
import com.java.inventoryservice.dto.InventoryResponse;
import com.java.inventoryservice.exception.InventoryNotFoundException;
import com.java.inventoryservice.entity.Inventory;
import com.java.inventoryservice.repository.InventoryRepository;
import com.java.inventoryservice.service.IInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class InventoryService implements IInventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public boolean isInstock(String skuCode) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(() -> new InventoryNotFoundException("Inventory Not Found !!!"));
        return inventory.getQuantity() == 0;
    }

    @Override
    @Transactional
    public List<InventoryResponse> findInventoryBySkucodeIn(List<String> skuCodes) {
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCodes);
        List<InventoryResponse> inventoryResponses = inventories.stream().map(this::mapToInventoryResponse).collect(Collectors.toList());
        return inventoryResponses;
    }

    @Override
    @Transactional(rollbackOn = IllegalArgumentException.class)
    public InventoryResponse addNewInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = mapToInventory(inventoryRequest);
        Inventory savedInventory = inventoryRepository.save(inventory);
        InventoryResponse inventoryResponse = mapToInventoryResponse(savedInventory);
        return inventoryResponse;
    }

    @Override
    @Transactional(rollbackOn = IllegalArgumentException.class)
    public List<InventoryResponse> addInventoryList(List<InventoryRequest> inventoriesRequest) {
        // 1. map to inventory
        List<Inventory> inventoryList = mapToInventoryList(inventoriesRequest);
        // 2. save list of inventory
        List<Inventory> savedInventoryList = inventoryRepository.saveAll(inventoryList);
        // 3. map to inventory response
        List<InventoryResponse> inventoryResponseList = mapToInventoryResponseList(savedInventoryList);
        // 4. return
        return inventoryResponseList;
    }

    private List<InventoryResponse> mapToInventoryResponseList(List<Inventory> savedInventoryList) {
        return savedInventoryList.stream().map(this::mapToInventoryResponse).collect(Collectors.toList());
    }

    private List<Inventory> mapToInventoryList(List<InventoryRequest> inventoriesRequest) {
        return inventoriesRequest.stream().map(this::mapToInventory).collect(Collectors.toList());
    }

    public InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .inventoryName(inventory.getInventoryName())
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .isInstock(inventory.getQuantity() > 0)
                .build();
    }

    @Transactional(rollbackOn = {Exception.class})
    public InventoryResponse addInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = mapToInventory(inventoryRequest);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return mapToInventoryResponse(savedInventory);
    }

    public Inventory mapToInventory(InventoryRequest inventoryRequest) {
        return Inventory.builder()
                .inventoryName(inventoryRequest.getInventoryName())
                .skuCode(UUID.randomUUID().toString())
                .quantity(inventoryRequest.getQuantity())
                .build();
    }
}
