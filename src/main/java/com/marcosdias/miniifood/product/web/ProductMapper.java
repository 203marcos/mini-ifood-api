package com.marcosdias.miniifood.product.web;

import com.marcosdias.miniifood.product.domain.Product;
import com.marcosdias.miniifood.product.web.dto.CreateProductRequest;
import com.marcosdias.miniifood.product.web.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequest request) {
        return Product.builder()
            .name(request.name())
            .description(request.description())
            .price(request.price())
            .quantityAvailable(request.quantityAvailable())
            .build();
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantityAvailable(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }
}

