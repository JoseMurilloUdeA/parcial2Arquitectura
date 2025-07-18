package com.udea.parcial_practico.product.factory;

import com.udea.parcial_practico.product.dto.ProductDto;
import com.udea.parcial_practico.product.entity.Product;
import com.udea.parcial_practico.storage.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory {

    public static Product createProduct(Long storageId, ProductDto productDto) {
        var store = Store.builder().id(storageId).build();
        var builder = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .initialAmount(productDto.getInitialAmount())
                .store(store);
        return  builder.build();
    }
}
