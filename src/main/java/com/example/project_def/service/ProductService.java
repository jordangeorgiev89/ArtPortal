package com.example.project_def.service;

import com.example.project_def.model.service.ProductServiceModel;
import com.example.project_def.model.service.UserServiceModel;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductServiceModel addProduct(ProductServiceModel psm) throws IOException;

    List<ProductServiceModel> getAllProducts();

}
