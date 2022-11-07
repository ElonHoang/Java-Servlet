package com.aptech.hw5.service.impl;

import java.util.List;

import com.aptech.hw5.model.Order;
import com.aptech.hw5.model.Product;
import com.aptech.hw5.model.User;
import com.aptech.hw5.repo.OrderRepo;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.OrderService;

public class OrderServiceImpl implements CRUDService<Order>,OrderService {
	OrderRepo repo = new OrderRepo();

	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order add(Order t) {
		// TODO Auto-generated method stub
		return repo.addOrder(t);
	}

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order delete(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAll(User user) {
		// TODO Auto-generated method stub
		return repo.getAll(user);
	}


}
