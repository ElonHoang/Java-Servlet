package com.aptech.hw5.service;

import com.aptech.hw5.model.User;

public interface UserService {
	public boolean checkLogin(String user, String pass);
	public User createAccount(User u);
}
