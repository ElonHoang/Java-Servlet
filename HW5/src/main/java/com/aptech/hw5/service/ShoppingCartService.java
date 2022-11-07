package com.aptech.hw5.service;

import java.util.List;

import com.aptech.hw5.model.Product;
import com.aptech.hw5.model.ShoppingCart;
import com.aptech.hw5.model.User;

public interface ShoppingCartService {
	public double getTotalCartPrice(List<ShoppingCart> cart, String[] check);

	public ShoppingCart addCart(Product p, User u, int quantity);

	public List<ShoppingCart> getAllCart(User username);

	public boolean deleteCart(int id);

	public boolean deleteCartByProductID(String id);

	public String updateQuantityInCart(String idProduct, int quantity);

	public int getQuantityByProductID(String product);

	public String getProductIDByProductID(String product);

}
