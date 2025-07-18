package com.udea.parcial_practico.product.dto;

import com.udea.parcial_practico.product.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Product}
 */
@Value
@Schema(description = "Datos del producto")
public class ProductDto implements Serializable {

    @Schema(description = "ID único del producto", example = "1")
    Long id;

    @Schema(description = "Nombre del producto", example = "Camiseta negra")
    String name;

    @Schema(description = "Descripción detallada del producto", example = "Camiseta de algodón, talla M")
    String description;

    @Schema(description = "Precio del producto", example = "39999.99")
    Double price;

    @Schema(description = "Cantidad inicial en inventario", example = "100.00")
    BigDecimal initialAmount;
}
