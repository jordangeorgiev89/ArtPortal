package com.example.project_def.service.impl;

import com.example.project_def.model.entity.Address;
import com.example.project_def.model.entity.Product;
import com.example.project_def.model.entity.URole;
import com.example.project_def.model.entity.User;
import com.example.project_def.model.service.ProductServiceModel;
import com.example.project_def.model.service.UserServiceModel;
import com.example.project_def.repository.AddressRepository;
import com.example.project_def.repository.ProductRepository;
import com.example.project_def.repository.URoleRepository;
import com.example.project_def.repository.UserRepository;
import com.example.project_def.service.UserService;
import com.example.project_def.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final URoleRepository uRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AddressRepository addressRepository;
    private final Util util;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, ProductRepository productRepository, URoleRepository uRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AddressRepository addressRepository, Util util) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.uRoleRepository = uRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.addressRepository = addressRepository;
        this.util = util;
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
            List<URole> uRoles = this.uRoleRepository.findAll();
            Set<URole> roles = new HashSet<URole>(uRoles);
            user.setAuthorities(roles);
        }else{
            URole uRole = this.uRoleRepository.findByAuthority("USER").orElse(null);
            Set<URole> roles = new HashSet<>();
            roles.add(uRole);
            user.setAuthorities(roles);
        }
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void buyProduct(String productId, String loggedUser) {

        User user = this.userRepository.findByUsername(loggedUser).orElse(null);
        Product product = this.productRepository.findById(productId).orElse(null);

        user.getBoughtProducts().add(product);

        this.userRepository.saveAndFlush(user);

    }

    @Override
    public boolean removeOneProductCart(String productId, String loggedUser) {
        User userFromDb = this.userRepository.findByUsername(loggedUser).orElse(null);
        if (userFromDb == null) {
//            throw new EX ("User with name " + loggedUser + " is not exist!");
        }
        Product product = this.productRepository.findById(productId).orElse(null);
        if (product == null) {
//            throw new ProductIsNotExistException("Product is not exist!");
        }
        List<Product> products = userFromDb.getBoughtProducts();
        products.remove(product);
        this.userRepository.saveAndFlush(userFromDb);
        return true;
    }

    @Override
    public UserServiceModel updateProfile(UserServiceModel usm) {
//        if (!usm.getPassword().equals(usm.getConfirmPassword())) {
//            throw new UserPasswordsNotMatchException("Password not match!");
//        }
        UserServiceModel returnUser = null;
        User u = this.userRepository.findByUsername(this.util.getLoggedUser()).orElse(null);
        if (u != null) {

//            if (usm.getPassword() != null && !"".equals(usm.getPassword()) &&
//                    usm.getConfirmPassword()!=null && !"".equals(usm.getConfirmPassword())){
//                u.setPassword(this.bCryptPasswordEncoder.encode(usm.getPassword()));
//            }
            u.setFirstName(usm.getFirstName());
            u.setLastName(usm.getLastName());
            u.setPhoneNumber(usm.getPhoneNumber());
            this.userRepository.saveAndFlush(u);
            Address userAddress = u.getAddress();
            if (userAddress != null) {
                userAddress.setCountry(usm.getCountry());
                userAddress.setCity(usm.getCity());
                userAddress.setPostCode(usm.getPostCode());
                userAddress.setStreet(usm.getStreet());
                userAddress.setStreetNumb(usm.getStreetNumb());
                this.addressRepository.saveAndFlush(userAddress);
                returnUser = this.modelMapper.map(userAddress, UserServiceModel.class);
            } else {
//                throw new AddressIsNotExistException("Address is not Exist (internal error)!");
            }
        } else {
//            throw new UserWithUsernameAlreadyExistException("User is not Exist (internal error)!");
        }
        return returnUser;



    }

    @Override
    public List<ProductServiceModel> getBoughtProducts() {
        User u = this.userRepository.findByUsername(this.util.getLoggedUser())
                .orElse(null);
        return u.getBoughtProducts().stream()
                .map(pr-> this.modelMapper.map(pr, ProductServiceModel.class))
                .collect(Collectors.toList());
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
