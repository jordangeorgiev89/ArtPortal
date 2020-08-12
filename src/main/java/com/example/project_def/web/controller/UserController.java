package com.example.project_def.web.controller;

import com.example.project_def.model.binding.UserRegisterBindingModel;
import com.example.project_def.model.service.UserServiceModel;
import com.example.project_def.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("urbm")UserRegisterBindingModel urbm) {

        UserServiceModel usm = this.modelMapper.map(urbm, UserServiceModel.class);

        this.userService.register(usm);
        System.out.println();


        return "redirect:/login";
    }

}
