package com.java.orderservice.controller;

import com.java.orderservice.dto.InventoryResponse;
import com.java.orderservice.dto.OrderLineItemsDto;
import com.java.orderservice.dto.OrderRequest;
import com.java.orderservice.dto.OrderResponse;
import com.java.orderservice.entity.OrderLineItems;
import com.java.orderservice.service.impl.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {

        orderService.placeOrder(orderRequest);

        return ResponseEntity.ok("Order Successfully");
    }
}