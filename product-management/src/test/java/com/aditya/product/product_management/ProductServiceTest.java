package com.aditya.product.product_management;

import com.aditya.product.product_management.entity.Product;
import com.aditya.product.product_management.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService service;

    @Test
    void testSaveAndRetrieveProduct() {
        Product product = new Product(null, "Laptop", "CO1001", 75000.0);
        Product saved = service.saveProduct(product);
        assertEquals("Laptop", saved.getName());
    }
}
