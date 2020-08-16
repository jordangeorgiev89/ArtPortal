package com.example.project_def.web.controller;

import com.example.project_def.model.binding.UserRegisterBindingModel;
import com.example.project_def.model.service.UserServiceModel;
import com.example.project_def.model.view.ProfileViewModel;
import com.example.project_def.repository.UserRepository;
import com.example.project_def.service.OrderService;
import com.example.project_def.service.UserService;
import com.example.project_def.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final Util util;
    private final UserRepository userRepository;
    private final OrderService orderService;


    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService, Util util, UserRepository userRepository, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.util = util;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("urbm")) {
            model.addAttribute("urbm", new UserRegisterBindingModel());
        }
        return "register";
    }


    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("urbm")UserRegisterBindingModel urbm,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.urbm",
                    bindingResult);
            redirectAttributes.addFlashAttribute("urbm", urbm);
            return "redirect:/register";
        }
        if (!urbm.getPassword().equals(urbm.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passwordsNotMatch", true);
            redirectAttributes.addFlashAttribute("urbm", urbm);
        }
        UserServiceModel usm = this.modelMapper.map(urbm, UserServiceModel.class);
        this.userService.register(usm);
        return "redirect:/login";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/user/buy/{productId}")
    public String addToCart(@PathVariable("productId") String productId) {
        System.out.println();
        if (!"anonymousUser".equals(this.util.getLoggedUser())) {
            this.userService.buyProduct(productId, this.util.getLoggedUser());
            return "redirect:/cart";
        }
        return "redirect:/home";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/remove-from-cart/{productId}")
    public String removeFromCart(@PathVariable("productId") String productId) {
        if (!"anonymousUser".equals(this.util.getLoggedUser())) {
            this.userService.removeOneProductCart(productId, this.util.getLoggedUser());
        }
        return "redirect:/cart";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/logout")
    public String logout() {
        return "/home";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/profile")
    public String userUpdateProfile(Model model) {


        UserServiceModel usm = this.userService.findByUsername(this.util.getLoggedUser());
        ProfileViewModel uvm = this.modelMapper.map(usm, ProfileViewModel.class);
        model.addAttribute("uvm", uvm);

        return "/profile";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/user/cart/finish")
    public String checkout() {
        this.orderService.writeOrder();
        return "/cart";
    }

//
//    @PostMapping("/profile")
//    public String userUpdateProfileConfirm(@ModelAttribute("upu") UserProfileUpdateBindingModel upu,
//                                           Model model) {
//        this.userService.updateProfile(this.modelMapper.map(upu, UserServiceModel.class));
//        return REDIRECT_TO_USER_PROFILE_UPDATE;
//    }

}
