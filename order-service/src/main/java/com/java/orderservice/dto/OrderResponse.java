package com.java.orderservice.dto;

import com.java.orderservice.entity.OrderLineItems;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class OrderResponse implements Serializable {
    private String orderNumber;

    private List<OrderLineItems> orderLineItemsList;
}
