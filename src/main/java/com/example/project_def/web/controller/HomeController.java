package com.example.project_def.web.controller;

import com.example.project_def.model.view.ProductViewModel;
import com.example.project_def.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ModelMapper modelMapper;
    private final ProductService productService;

    @Autowired
    public HomeController(ModelMapper modelMapper, ProductService productService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
    }


    @GetMapping("/")
    public String home(Model model) {
        List<ProductViewModel> allProducts =
                this.productService.getAllProducts()
                .stream().map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("allProducts", allProducts);
        return "home";
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
