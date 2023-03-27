package com.testsigma.crud.repository;

import com.testsigma.crud.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface productRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    List<Product> findAll(Specification<Product> specification);
}
