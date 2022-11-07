package com.aptech.hw5.model;

import java.util.Objects;

public class ShoppingCart {
	private int id;
	private Product product;
	private User user;
	private int quantity;
	private double total_price;
	public ShoppingCart() {

	}
	public ShoppingCart(int id, Product product, User user, int quantity, int total_price) {
		this.id = id;
		this.product = product;
		this.user = user;
		this.quantity = quantity;
		this.total_price = total_price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	
	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", product=" + product + ", user=" + user + ", quantity=" + quantity
				+ ", total_price=" + total_price + "]";
	}
	
	

}
