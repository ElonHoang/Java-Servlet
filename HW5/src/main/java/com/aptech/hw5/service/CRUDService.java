package com.aptech.hw5.service;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T> {
	public List<T> getAll();

	public T add(T t);

	public T update(T t);

	public T delete(T t);
	
	public T getById(String id);
}
