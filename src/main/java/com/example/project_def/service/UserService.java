package com.example.project_def.service;

import com.example.project_def.model.service.ProductServiceModel;
import com.example.project_def.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserServiceModel findByUsername(String username);

    void register(UserServiceModel usm);

    void buyProduct(String productId, String loggedUser);

    boolean removeOneProductCart(String productId, String loggedUser);

    UserServiceModel updateProfile(UserServiceModel usm);

    List<ProductServiceModel> getBoughtProducts();


}
