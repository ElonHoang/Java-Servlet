package com.aptech.hw5.model;

import java.util.Objects;

public class User {
	private String user;
	private String pass;

	public User() {
	}

	public User(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pass, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(pass, other.pass) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "User [user=" + user + ", pass=" + pass + "]";
	}
	
	

}
