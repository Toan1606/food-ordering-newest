package com.java.productservice.service;

import com.java.productservice.dto.ProductRequest;
import com.java.productservice.dto.ProductResponse;
import com.java.productservice.entity.Product;

import java.util.List;

public interface IProductService {

    public List<ProductResponse> findAllProduct();

    public Product addProduct(ProductRequest productDTO);

    public ProductResponse findProductByName(String productName);
}
