package com.example.project_def.web.controller;

import com.example.project_def.model.binding.ProductBindingModel;
import com.example.project_def.model.binding.UserRegisterBindingModel;
import com.example.project_def.model.service.ProductServiceModel;
import com.example.project_def.model.service.UserServiceModel;
import com.example.project_def.service.ProductService;
import com.example.project_def.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ProductController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ProductService productService;

    public ProductController(ModelMapper modelMapper, UserService userService, ProductService productService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.productService = productService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/product-add")
    public String login() {
        return "product-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/product-add")
    public String registerPost(@ModelAttribute("pbm") ProductBindingModel pbm) throws IOException {

        ProductServiceModel psm = this.modelMapper.map(pbm, ProductServiceModel.class);

        this.productService.addProduct(psm);
        System.out.println();

        return "redirect:/";
    }


}
