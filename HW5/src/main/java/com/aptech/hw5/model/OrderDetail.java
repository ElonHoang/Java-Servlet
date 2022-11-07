package com.aptech.hw5.model;

import java.util.Objects;

public class OrderDetail {
	private Order order;
	private Product product;
	private int quantity;
	private int price;

	public OrderDetail() {

	}

	public OrderDetail(Order order, Product product, int quantity, int price) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, price, product, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		return Objects.equals(order, other.order) && price == other.price && Objects.equals(product, other.product)
				&& quantity == other.quantity;
	}

	@Override
	public String toString() {
		return "OrderDetail [order=" + order + ", product=" + product + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}

}
