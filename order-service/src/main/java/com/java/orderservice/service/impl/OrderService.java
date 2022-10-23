package com.java.orderservice.service.impl;

import com.java.orderservice.dto.InventoryResponse;
import com.java.orderservice.dto.OrderLineItemsDto;
import com.java.orderservice.dto.OrderRequest;
import com.java.orderservice.dto.OrderResponse;
import com.java.orderservice.entity.Order;
import com.java.orderservice.entity.OrderLineItems;
import com.java.orderservice.exception.InventoryCanNotInstockToOrder;
import com.java.orderservice.repository.OrderRepository;
import com.java.orderservice.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

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

    @Override
    public void placeOrder(OrderRequest orderRequest) {

        List<OrderLineItemsDto> orderLineItemsDtoList = orderRequest.getOrderLineItemsDtoList();

        List<OrderLineItems> orderLineItems = orderLineItemsDtoList.stream().map(orderRequest::mapToOrderLineItems).collect(Collectors.toList());

        Order order = Order.builder()
                .orderId(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItems)
                .build();

        List<String> skuCodes = orderLineItems.stream().map(this::getSkuCode).collect(Collectors.toList());

        InventoryResponse[] inventoryResponse = webClient.get()
                .uri("localhost:8082/api/v1/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInstock = Arrays.stream(inventoryResponse).allMatch(InventoryResponse::isInstock);

        if (allProductsInstock) {
            orderRepository.save(order);
        } else {
            throw new InventoryCanNotInstockToOrder("Inventory Is Not In Stock To Order");
        }
    }

    public String getSkuCode(OrderLineItems orderLineItems) {
        return orderLineItems.getSkuCode();
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