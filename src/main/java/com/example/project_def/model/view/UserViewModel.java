package com.example.project_def.model.view;

import com.example.project_def.model.entity.Address;
import com.example.project_def.model.entity.Product;
import com.example.project_def.model.entity.URole;

import java.util.List;
import java.util.Set;

public class UserViewModel extends BaseViewModel {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private List<Product> boughtProducts;
    private Set<URole> authorities;

    public UserViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Set<URole> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<URole> authorities) {
        this.authorities = authorities;
    }

}
