package com.marcosdias.miniifood.product.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CreateProductRequest(
    @NotBlank
    @Size(max = 200)
    @Schema(description = "Product name", example = "Burger")
    String name,

    @Size(max = 1000)
    @Schema(description = "Product description", example = "Delicious burger with cheese")
    String description,

    @NotNull
    @DecimalMin("0.01")
    @Schema(description = "Product price", example = "25.90")
    BigDecimal price,

    @NotNull
    @PositiveOrZero
    @Schema(description = "Quantity available in stock", example = "100")
    Integer quantityAvailable
) {
}

