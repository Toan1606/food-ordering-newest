package com.java.inventoryservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryRequest implements Serializable {
    private String skuCode;

    private Integer quantity;
}
