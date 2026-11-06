package com.aditya.product.product_management;

import com.aditya.product.product_management.controller.ProductController;
import com.aditya.product.product_management.entity.Product;
import com.aditya.product.product_management.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build();
    }

    @Test
    void testGetAllProducts() throws Exception {
        Product product = new Product(1L, "Laptop", "C01001", 75000.0);
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[0].code").value("C01001"));
    }

    @Test
    void testGetProductByCode() throws Exception {
        Product product = new Product(1L, "Laptop", "C01001", 75000.0);
        when(productService.getProductByCode("C01001")).thenReturn(product);

        mockMvc.perform(get("/api/products/C01001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.price").value(75000.0));
    }

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product(1L, "Laptop", "C01001", 75000.0);
        when(productService.saveProduct(product)).thenReturn(product);

        String productJson = """
            {
                "id": 1,
                "name": "Laptop",
                "code": "C01001",
                "price": 75000.0
            }
            """;

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.code").value("C01001"))
                .andExpect(jsonPath("$.price").value(75000.0));
    }

    @Test
    void testDeleteProduct() throws Exception {
        // Mock service to do nothing when delete is called
        doNothing().when(productService).deleteProduct("C01001");

        mockMvc.perform(delete("/api/products/C01001"))
                .andExpect(status().isOk());
    }
}
