package com.example.project_def.service.impl;

import com.example.project_def.enums.CategoryName;
import com.example.project_def.model.entity.Category;
import com.example.project_def.repository.CategoryRepository;
import com.example.project_def.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    @Override
    public void seedAllCategories() {
        if (this.categoryRepository.count() == 0) {
            for (CategoryName value : CategoryName.values()) {
                this.categoryRepository.saveAndFlush(new Category(value.getName(),
                        value.getDescription()));
            }
        }
    }
}
