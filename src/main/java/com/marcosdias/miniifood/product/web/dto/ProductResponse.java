package com.marcosdias.miniifood.product.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ProductResponse(
    @Schema(description = "Product ID", example = "1")
    Long id,

    @Schema(description = "Product name", example = "Burger")
    String name,

    @Schema(description = "Product description", example = "Delicious burger with cheese")
    String description,

    @Schema(description = "Product price", example = "25.90")
    BigDecimal price,

    @Schema(description = "Quantity available in stock", example = "100")
    Integer quantityAvailable,

    @Schema(description = "Creation timestamp")
    OffsetDateTime createdAt,

    @Schema(description = "Last update timestamp")
    OffsetDateTime updatedAt
) {
}

