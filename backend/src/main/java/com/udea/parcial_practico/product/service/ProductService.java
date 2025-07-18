package com.udea.parcial_practico.product.service;

import com.udea.parcial_practico.product.dto.ProductDto;
import com.udea.parcial_practico.product.entity.Product;
import com.udea.parcial_practico.product.factory.ProductFactory;
import com.udea.parcial_practico.product.mapper.ProductMapper;
import com.udea.parcial_practico.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;
    @Autowired
    public ProductService(IProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getProductsByStoreId(Long storeId) {
        return productRepository.findAllByStore_Id(storeId)
                .stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getInitialAmount()))
                .toList();
    }

    public ProductDto createProduct(Long storageId, ProductDto productDto) {
        var product = ProductFactory.createProduct(storageId, productDto);
        return productMapper.toDto(productRepository.save(product));
    }
}
