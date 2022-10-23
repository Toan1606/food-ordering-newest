package com.java.inventoryservice.service;

import com.java.inventoryservice.dto.InventoryRequest;
import com.java.inventoryservice.dto.InventoryResponse;
import com.java.inventoryservice.entity.Inventory;

import java.util.List;

public interface IInventoryService {

    public boolean isInstock(String skuCode);

    List<InventoryResponse> findInventoryBySkucodeIn(List<String> skuCodes);

    InventoryResponse addNewInventory(InventoryRequest inventoryRequest);

    List<InventoryResponse> addInventoryList(List<InventoryRequest> inventoriesRequest);
}
