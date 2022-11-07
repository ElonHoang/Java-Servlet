package com.aptech.hw5.service.impl;

import java.util.List;
import java.util.Optional;

import com.aptech.hw5.model.Category;
import com.aptech.hw5.repo.CategoryRepo;
import com.aptech.hw5.service.CRUDService;

public class CategoryServiceImpl implements CRUDService<Category> {

	public CategoryRepo repo = new CategoryRepo();

	@Override
	public List<Category> getAll() {
		return repo.getCategories();
	}

	@Override
	public Category add(Category t) {
		return repo.addCategory(t);
	}

	@Override
	public Category update(Category t) {
		return repo.updateCategory(t);
	}

	@Override
	public Category delete(Category t) {
		return repo.deleteCategory(t);
	}

	@Override
	public Category getById(String id) {
		return repo.getCategoryById(Integer.valueOf(id));
	}

}
