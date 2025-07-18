package com.udea.parcial_practico.product.controller;

import com.udea.parcial_practico.product.dto.ProductDto;
import com.udea.parcial_practico.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/storage", headers = "X-API-VERSION=1")
@Tag(name = "Productos", description = "Operaciones relacionadas con productos en un almacén")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Crear un producto en un almacén",
            description = "Agrega un nuevo producto a un almacén específico.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos enviados"),
                    @ApiResponse(responseCode = "404", description = "Almacén no encontrado")
            }
    )
    @GetMapping("{storageId}/products")
    public CollectionModel<EntityModel<ProductDto>> getProducts(@PathVariable Long storageId) {
        List<ProductDto> products = productService.getProductsByStoreId(storageId);

        List<EntityModel<ProductDto>> productModels = products.stream()
                .map(product -> EntityModel.of(product,
                        linkTo(methodOn(ProductController.class)
                                .getProducts(storageId))
                                .withSelfRel()
                ))
                .toList();

        return CollectionModel.of(
                productModels,
                linkTo(methodOn(ProductController.class)
                        .getProducts(storageId))
                        .withSelfRel()
        );
    }

    @Operation(
            summary = "Crear un producto en un almacén",
            description = "Agrega un nuevo producto a un almacén específico.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos enviados"),
                    @ApiResponse(responseCode = "404", description = "Almacén no encontrado")
            }
    )

    @PostMapping("/{storageId}/products")
    public ResponseEntity<EntityModel<ProductDto>> createProduct(
            @Parameter(description = "ID del almacén", required = true)
            @PathVariable Long storageId,

            @RequestBody(
                    description = "Datos del nuevo producto",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ProductDto.class))
            )
            @org.springframework.web.bind.annotation.RequestBody ProductDto productDto
    ) {
        ProductDto createdProduct = productService.createProduct(storageId, productDto);

        EntityModel<ProductDto> model = EntityModel.of(createdProduct,
                linkTo(methodOn(ProductController.class).createProduct(storageId, productDto)).withSelfRel(),
                linkTo(methodOn(ProductController.class).getProducts(storageId)).withRel("all-products")
        );

        return ResponseEntity.created(
                        linkTo(methodOn(ProductController.class).getProducts(storageId)).toUri())
                .body(model);
    }
}
