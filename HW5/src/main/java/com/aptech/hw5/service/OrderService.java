package com.aptech.hw5.service;

import java.util.List;

import com.aptech.hw5.model.Order;
import com.aptech.hw5.model.User;

public interface OrderService {
	public List<Order> getAll(User user);
}
