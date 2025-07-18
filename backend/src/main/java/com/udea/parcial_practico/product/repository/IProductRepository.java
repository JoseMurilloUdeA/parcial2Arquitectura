package com.udea.parcial_practico.product.repository;

import com.udea.parcial_practico.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStore_Id(Long storeId);
}
