package com.aptech.hw5.service.impl;

import com.aptech.hw5.model.User;
import com.aptech.hw5.repo.UserRepo;
import com.aptech.hw5.service.UserService;

public class UserServiceImpl implements UserService {
	UserRepo repo = new UserRepo();
	@Override
	public boolean checkLogin(String user, String pass) {
		return repo.checkLogin(user, pass);
	}
	@Override
	public User createAccount(User u) {
		// TODO Auto-generated method stub
		return repo.createAccount(u);
	}

}
