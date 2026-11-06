package com.aditya.product.product_management.service;

import com.aditya.product.product_management.entity.Product;
import com.aditya.product.product_management.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public void deleteProduct(String code) {
        Product product = getProductByCode(code);
        repository.delete(product);
    }
}
