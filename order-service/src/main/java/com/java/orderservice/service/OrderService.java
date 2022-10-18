package com.java.orderservice.service;

import com.java.orderservice.dto.OrderLineItemsDto;
import com.java.orderservice.dto.OrderRequest;
import com.java.orderservice.dto.OrderResponse;
import com.java.orderservice.model.Order;
import com.java.orderservice.model.OrderLineItems;
import com.java.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;


    @Override
    @Transactional(rollbackOn = {Exception.class})
    public OrderResponse addOrder(OrderRequest orderRequest) {
        List<OrderLineItemsDto> orderLineItemsDtoList = orderRequest.getOrderLineItemsDtoList();
        List<OrderLineItems> orderLineItemsList = orderLineItemsDtoList.stream().map(this::mapToOrderLineItems).collect(Collectors.toList());
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItemsList)
                .build();
        return mapToOrderResponse(order);
    }

    public OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .orderNumber(order.getOrderNumber())
                .orderLineItemsList(order.getOrderLineItemsList())
                .build();
    }

    public OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .skuCode(orderLineItemsDto.getSkuCode())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .build();
    }
}
