package com.aptech.hw5.service.impl;

import java.util.List;

import com.aptech.hw5.model.OrderDetail;
import com.aptech.hw5.repo.OrderDetailRepo;
import com.aptech.hw5.service.CRUDService;
import com.aptech.hw5.service.OrderDetailService;

public class OrderDetailServiceImpl implements CRUDService<OrderDetail>, OrderDetailService {
	OrderDetailRepo repo = new OrderDetailRepo();

	@Override
	public List<OrderDetail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetail add(OrderDetail t) {
		// TODO Auto-generated method stub
		return repo.addOrderDetail(t);
	}

	@Override
	public OrderDetail update(OrderDetail t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetail delete(OrderDetail t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetail getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> getAllByOrderID(int id) {
		// TODO Auto-generated method stub
		return repo.getAllByOrderID(id);
	}

}
