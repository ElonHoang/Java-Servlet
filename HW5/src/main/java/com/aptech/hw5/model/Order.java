package com.aptech.hw5.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
	private int id;
	private User user;
	private int total_price;
	private LocalDateTime datetime;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int id, User user, int total_price, LocalDateTime datetime) {
		super();
		this.id = id;
		this.user = user;
		this.total_price = total_price;
		this.datetime = datetime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	@Override
	public int hashCode() {
		return Objects.hash(datetime, id, total_price, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(datetime, other.datetime) && id == other.id && total_price == other.total_price
				&& Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", total_price=" + total_price + ", datetime=" + datetime + "]";
	}

	
}
