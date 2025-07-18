package com.udea.parcial_practico.product.mapper;

import com.udea.parcial_practico.product.entity.Product;
import com.udea.parcial_practico.product.dto.ProductDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Product partialUpdate(ProductDto productDto, @MappingTarget Product product);
}