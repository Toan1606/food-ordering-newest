package com.java.productservice.controller;

import com.java.productservice.dto.ProductRequest;
import com.java.productservice.dto.ProductResponse;
import com.java.productservice.entity.Product;
import com.java.productservice.service.iml.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RefreshScope
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        log.info("findAll() method to display ");
        return ResponseEntity.ok(productService.findAllProduct());
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest productRequest) {
        log.info("addProduct() method to add " + productRequest);
        Product product = productService.addProduct(productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
