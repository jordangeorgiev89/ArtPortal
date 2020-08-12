package com.example.project_def.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

	private String productName;
	private String phoneNumber;
	private String username;
	private String firstName;
	private String lastName;
	private String userCountry;
	private String userCity;
	private String userPostCode;
	private String userStreet;
	private String userStreetNumber;
	private String note;
	private double price;
	private LocalDateTime buyDate = LocalDateTime.now();

	public Order() {
		super();
	}

	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "user_county")
	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	@Column(name = "user_city")
	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	@Column(name = "post_code")
	public String getUserPostCode() {
		return userPostCode;
	}

	public void setUserPostCode(String userPostCode) {
		this.userPostCode = userPostCode;
	}

	@Column(name = "user_street")
	public String getUserStreet() {
		return userStreet;
	}

	public void setUserStreet(String userStreet) {
		this.userStreet = userStreet;
	}

	@Column(name = "user_street_number")
	public String getUserStreetNumber() {
		return userStreetNumber;
	}

	public void setUserStreetNumber(String userStreetNumber) {
		this.userStreetNumber = userStreetNumber;
	}

	@Column(name = "note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(LocalDateTime buyDate) {
		this.buyDate = buyDate;
	}
}
