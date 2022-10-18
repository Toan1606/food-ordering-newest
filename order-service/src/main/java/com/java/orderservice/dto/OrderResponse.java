package com.java.orderservice.dto;

import com.java.orderservice.model.OrderLineItems;
import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class OrderResponse implements Serializable {
    private String orderNumber;

    private List<OrderLineItems> orderLineItemsList;
}
