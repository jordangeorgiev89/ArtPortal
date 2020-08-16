package com.example.project_def.service.impl;

import com.example.project_def.model.entity.Category;
import com.example.project_def.model.entity.Product;
import com.example.project_def.model.service.ProductServiceModel;
import com.example.project_def.repository.CategoryRepository;
import com.example.project_def.repository.ProductRepository;
import com.example.project_def.repository.UserRepository;
import com.example.project_def.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ProductServiceModel addProduct(ProductServiceModel psm) throws IOException {
        Product product = this.modelMapper.map(psm, Product.class);
        product.setAuthor(psm.getAuthor());
        product.setDescription(psm.getDescription());
        product.setMake(psm.getMake());
        product.setName(psm.getName());
        product.setCategory(psm.getCategory());
        product.setQuantity(psm.getQuantity());
        product.setPrice(psm.getPrice());
        Category c = this.categoryRepository.findByName(psm.getCategoryStr()).orElse(null);
        product.setCategory(c);
        Product savedProduct = this.productRepository.saveAndFlush(product);
        return this.modelMapper.map(savedProduct, ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> getAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }
}
