package com.java.inventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryRequest implements Serializable {

    @JsonProperty("inventory_name")
    private String inventoryName;

    @JsonProperty("quantity")
    private Integer quantity;
}
