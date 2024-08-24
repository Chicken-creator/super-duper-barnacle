package com.ecommmerce.project.service;

import com.ecommmerce.project.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    void createProduct(Product product);
    String deleteProduct(Long productId);
    Product updateProduct(Product product, Long productId);
    List<Product> getProductsByCategory(Long categoryId);
}
