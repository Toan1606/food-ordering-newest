package com.java.productservice.repository;

import com.java.productservice.dto.ProductResponse;
import com.java.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    ProductResponse findByProductName(String productName);
}
