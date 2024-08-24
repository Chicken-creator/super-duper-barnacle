package com.ecommmerce.project.service;

import com.ecommmerce.project.model.Category;
import com.ecommmerce.project.model.Product;
import com.ecommmerce.project.repositories.CategoryRepository;
import com.ecommmerce.project.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(final ProductRepository productRepository, final CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(final Product product) {
        if (product.getCategory() != null) {
            Category category = categoryRepository.findById(product.getCategory().getCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
            product.setCategory(category);
        }
        productRepository.save(product);
    }

    @Override
    public String deleteProduct(final Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        productRepository.delete(product);
        return "Product with productId: " + productId + " deleted successfully !!";
    }

    @Override
    public Product updateProduct(final Product product, final Long productId) {
        Product savedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        savedProduct.setProductName(product.getProductName());
        savedProduct.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            Category category = categoryRepository.findById(product.getCategory().getCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
            savedProduct.setCategory(category);
        }
        productRepository.save(savedProduct);
        return savedProduct;
    }

    @Override
    public List<Product> getProductsByCategory(final Long categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId);
    }
}
