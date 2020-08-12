package com.example.project_def.service;

import com.example.project_def.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserServiceModel findByUsername(String username);

    void register(UserServiceModel usm);
}
