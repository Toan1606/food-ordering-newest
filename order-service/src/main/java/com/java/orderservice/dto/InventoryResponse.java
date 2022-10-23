package com.java.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse implements Serializable {

    private String skuCode;

    private String inventoryName;

    private boolean isInstock;

    private Integer quantity;

}
