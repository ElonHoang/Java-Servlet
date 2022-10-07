package com.aptech.hw5.service;

import java.util.List;
import java.util.Optional;

import com.aptech.hw5.model.Product;
import com.aptech.hw5.repo.ProductRepo;

public class ProductServiceImpl implements CRUDService<Product> {

	ProductRepo repo = new ProductRepo();
	@Override
	public List<Product> getAll() {
		return repo.getProducts();
	}

	@Override
	public Product add(Product t) {
		return repo.addProduct(t);
	}

	@Override
	public Product update(Product t) {
		// TODO Auto-generated method stub
		return repo.updateProduct(t);
	}

	@Override
	public Product delete(Product t) {
		// TODO Auto-generated method stub
		return repo.deleteProduct(t);
	}

	@Override
	public Product getById(String id) {
		// TODO Auto-generated method stub
		return repo.getProductById(id);
	}

}
