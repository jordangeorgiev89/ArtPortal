package com.example.project_def.repository;

import com.example.project_def.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository <Category, String> {
    Optional<Category> findByName (String name);

}
