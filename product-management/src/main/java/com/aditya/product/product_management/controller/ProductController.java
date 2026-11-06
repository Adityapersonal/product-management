package com.aditya.product.product_management.controller;

import com.aditya.product.product_management.entity.Product;
import com.aditya.product.product_management.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{code}")
    public Product getProduct(@PathVariable String code) {
        return service.getProductByCode(code);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }
 @DeleteMapping("/{code}")
 public ResponseEntity<Void> deleteProduct(@PathVariable String code) {
    service.deleteProduct(code);
    return ResponseEntity.ok().build();
 }

}
