package com.marcosdias.miniifood.product.web;

import com.marcosdias.miniifood.product.domain.Product;
import com.marcosdias.miniifood.product.service.ProductService;
import com.marcosdias.miniifood.product.web.dto.CreateProductRequest;
import com.marcosdias.miniifood.product.web.dto.ProductPageResponse;
import com.marcosdias.miniifood.product.web.dto.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product management endpoints")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    @Operation(summary = "List all products", description = "Get paginated list of all products")
    @ApiResponse(responseCode = "200", description = "List of products")
    public ResponseEntity<ProductPageResponse> findAll(
        @PageableDefault(size = 10)
        @Parameter(description = "Pagination parameters")
        Pageable pageable) {

        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieve a specific product by its ID")
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<ProductResponse> findById(
        @PathVariable
        @Parameter(description = "Product ID")
        Long id) {

        Product product = productService.findById(id);
        return ResponseEntity.ok(productMapper.toResponse(product));
    }

    @PostMapping
    @Operation(summary = "Create new product", description = "Create a new product in the system")
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "409", description = "Product with same name already exists")
    public ResponseEntity<ProductResponse> create(
        @RequestBody
        @Valid
        CreateProductRequest request) {

        Product product = productMapper.toEntity(request);

        Product created = productService.create(product);
        URI location = URI.create("/api/products/" + created.getId());

        return ResponseEntity.created(location).body(productMapper.toResponse(created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product", description = "Update an existing product")
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "409", description = "Product with same name already exists")
    public ResponseEntity<ProductResponse> update(
        @PathVariable
        @Parameter(description = "Product ID")
        Long id,
        @RequestBody
        @Valid
        CreateProductRequest request) {

        Product productData = productMapper.toEntity(request);

        Product updated = productService.update(id, productData);
        return ResponseEntity.ok(productMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Remove a product from the system")
    @ApiResponse(responseCode = "204", description = "Product deleted successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Void> delete(
        @PathVariable
        @Parameter(description = "Product ID")
        Long id) {

        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

