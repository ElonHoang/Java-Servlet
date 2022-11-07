package com.aptech.hw5.service;

import java.util.List;

import com.aptech.hw5.model.OrderDetail;

public interface OrderDetailService {
	public List<OrderDetail> getAllByOrderID(int id);
}
