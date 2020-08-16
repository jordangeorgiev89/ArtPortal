package com.example.project_def.web.controller;

import com.example.project_def.model.service.ProductServiceModel;
import com.example.project_def.model.view.ProductViewModel;
import com.example.project_def.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public CartController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/cart")
    public String cart(Model model) {
        List<ProductViewModel> allBoughtProducts =
                this.userService.getBoughtProducts()
                        .stream()
                        .map(pr -> this.modelMapper.map(pr, ProductViewModel.class))
                        .collect(Collectors.toList());
        model.addAttribute("allBoughtProducts", allBoughtProducts);
        return "cart";
    }
}
