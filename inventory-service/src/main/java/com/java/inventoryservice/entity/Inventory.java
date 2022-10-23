package com.java.inventoryservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @JsonProperty("inventory_name")
    private String inventoryName;

    @Column(name = "sku_code", unique = true)
    private String skuCode;

    @Column(name = "quantity")
    private Integer quantity;
}
