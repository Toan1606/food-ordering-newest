package com.java.productservice.service.iml;

import com.java.productservice.dto.ProductRequest;
import com.java.productservice.dto.ProductResponse;
import com.java.productservice.entity.Product;
import com.java.productservice.repository.ProductRepository;
import com.java.productservice.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> findAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    public ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        Product product = Product.builder().productName(productRequest.getProductName()).price(productRequest.getPrice()).description(productRequest.getDescription()).build();
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public ProductResponse findProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }
}
