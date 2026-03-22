package com.marcosdias.miniifood.product.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.marcosdias.miniifood.product.domain.Product;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldPersistAndFindProductByName() {
        Product product = Product.builder()
            .name("Burger")
            .description("Delicious burger")
            .price(BigDecimal.valueOf(25.90))
            .quantityAvailable(100)
            .build();

        Product saved = productRepository.save(product);

        assertNotNull(saved.getId());
        assertTrue(productRepository.existsByNameIgnoreCase("burger"));
    }

    @Test
    void shouldCheckIfProductNameExists() {
        Product product = Product.builder()
            .name("Pizza")
            .price(BigDecimal.valueOf(35.90))
            .quantityAvailable(50)
            .build();

        productRepository.save(product);

        assertTrue(productRepository.existsByNameIgnoreCase("PIZZA"));
        assertTrue(productRepository.existsByNameIgnoreCase("pizza"));
    }
}

