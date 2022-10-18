package com.java.orderservice.dto;

import com.java.orderservice.model.OrderLineItems;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class OrderRequest implements Serializable {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
