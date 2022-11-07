package com.aptech.hw5.service.impl;

import java.util.List;

import com.aptech.hw5.model.Product;
import com.aptech.hw5.model.ShoppingCart;
import com.aptech.hw5.model.User;
import com.aptech.hw5.repo.ShoppingCartRepo;
import com.aptech.hw5.service.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService {
	public ShoppingCartRepo repo = new ShoppingCartRepo();

	@Override
	public double getTotalCartPrice(List<ShoppingCart> cart, String[] check) {
		// TODO Auto-generated method stub
		return repo.getTotalCartPrice(cart, check);
	}

	@Override
	public ShoppingCart addCart(Product p, User u, int quantity) {
		// TODO Auto-generated method stub
		return repo.addCart(p, u, quantity);
	}

	@Override
	public List<ShoppingCart> getAllCart(User u) {
		// TODO Auto-generated method stub
		return repo.getAllCart(u);
	}

	@Override
	public boolean deleteCart(int id) {
		// TODO Auto-generated method stub
		return repo.deleteCart(id);
	}

	@Override
	public String updateQuantityInCart(String idProduct, int quantity) {
		// TODO Auto-generated method stub
		return repo.updateQuantityInCart(idProduct, quantity);
	}

	@Override
	public int getQuantityByProductID(String product) {
		// TODO Auto-generated method stub
		return repo.getQuantityByProductID(product);
	}

	@Override
	public String getProductIDByProductID(String product) {
		// TODO Auto-generated method stub
		return repo.getProductIDByProductID(product);
	}

	@Override
	public boolean deleteCartByProductID(String id) {
		// TODO Auto-generated method stub
		return repo.deleteCartByProductID(id);
	}





}
