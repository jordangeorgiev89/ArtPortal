package com.example.project_def.service.impl;

import com.example.project_def.model.entity.Address;
import com.example.project_def.model.entity.URole;
import com.example.project_def.model.entity.User;
import com.example.project_def.model.service.UserServiceModel;
import com.example.project_def.repository.URoleRepository;
import com.example.project_def.repository.UserRepository;
import com.example.project_def.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final URoleRepository uRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, URoleRepository uRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.uRoleRepository = uRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return null;
    }

    @Override
    public void register(UserServiceModel usm) {
        Address address = new Address();
        address.setCountry(usm.getCountry());
        address.setCity(usm.getCity());
        address.setPostCode(usm.getPostCode());
        address.setStreet(usm.getStreet());
        address.setStreetNumb(usm.getStreetNumb());
        User user = this.modelMapper.map(usm, User.class);
        String newPass = this.bCryptPasswordEncoder.encode(usm.getPassword());
        user.setPassword(newPass);
        user.setAddress(address);
        if (this.userRepository.count() == 0){
            URole uRole = this.uRoleRepository.findByAuthority("ADMIN").orElse(null);
            Set<URole> roles = new HashSet<>();
            roles.add(uRole);
            user.setAuthorities(roles);
        }else{
            List<URole> uRoles = this.uRoleRepository.findAll();
            Set<URole> roles = new HashSet<URole>(uRoles);
            user.setAuthorities(roles);
        }
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDb = this.userRepository.findByUsername(username).orElse(null);

        if (userFromDb == null) {
//            throw new Exception()
        }

        return userFromDb;
    }
}
