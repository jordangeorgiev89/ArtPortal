package com.example.project_def.repository;

import com.example.project_def.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <Product, String> {
    Optional<Product> findByName (String name);
}
