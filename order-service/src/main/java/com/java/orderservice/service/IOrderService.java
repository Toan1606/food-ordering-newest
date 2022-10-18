package com.java.orderservice.service;

import com.java.orderservice.dto.OrderRequest;
import com.java.orderservice.dto.OrderResponse;
import com.java.orderservice.model.Order;

public interface IOrderService {
    public OrderResponse addOrder(OrderRequest orderRequest);
}
