package com.java.inventoryservice.exception;

public class InventoryNotFoundException extends RuntimeException  {

    public InventoryNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
