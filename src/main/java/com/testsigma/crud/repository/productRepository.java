package com.testsigma.crud.repository;

import com.testsigma.crud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);
}
