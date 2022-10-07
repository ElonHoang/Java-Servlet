package com.aptech.hw5.service;

import com.aptech.hw5.repo.UserRepo;

public class UserServiceImpl implements UserService {
	UserRepo repo = new UserRepo();
	@Override
	public boolean checkLogin(String user, String pass) {
		return repo.checkLogin(user, pass);
	}

}
