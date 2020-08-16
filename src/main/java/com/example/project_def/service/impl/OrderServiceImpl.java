package com.example.project_def.service.impl;

import com.example.project_def.model.entity.Order;
import com.example.project_def.model.entity.Product;
import com.example.project_def.model.entity.User;
import com.example.project_def.model.service.UserServiceModel;
import com.example.project_def.repository.OrderRepository;
import com.example.project_def.repository.UserRepository;
import com.example.project_def.service.OrderService;
import com.example.project_def.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final Util util;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserRepository userRepository, Util util) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.util = util;
    }


    @Override
    public void writeOrder() {

        User u =
                this.userRepository.findByUsername(
                        this.util.getLoggedUser()).orElse(null);

        List<Product> products = u.getBoughtProducts();

        for (Product product : products) {
            Order order = new Order();

            order.setProductName(product.getName());
            order.setPrice(product.getPrice().doubleValue());
            order.setFirstName(u.getFirstName());
            order.setLastName(u.getLastName());
            order.setNote("");
            order.setPhoneNumber(u.getPhoneNumber());
            order.setUserStreetNumber(u.getAddress().getStreetNumb());
            order.setUserStreet(u.getAddress().getStreet());
            order.setUserCountry(u.getAddress().getCountry());
            order.setUserCity(u.getAddress().getCity());
            order.setUserPostCode(u.getAddress().getCity());
            order.setUsername(u.getUsername());

            this.orderRepository.saveAndFlush(order);
        }

        u.getBoughtProducts().clear();
        this.userRepository.saveAndFlush(u);
    }
}
