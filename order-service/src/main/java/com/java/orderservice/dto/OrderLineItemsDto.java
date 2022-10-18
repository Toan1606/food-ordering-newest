package com.java.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class OrderLineItemsDto implements Serializable {
    private String skuCode;

    private BigDecimal price;

    private Integer quantity;
}
