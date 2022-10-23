package com.java.orderservice.exception;

public class InventoryCanNotInstockToOrder extends IllegalArgumentException {

    public InventoryCanNotInstockToOrder(String errorMessage) {
        super(errorMessage);
    }
}
