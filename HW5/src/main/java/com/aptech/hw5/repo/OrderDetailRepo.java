package com.aptech.hw5.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aptech.hw5.model.Order;
import com.aptech.hw5.model.OrderDetail;
import com.aptech.hw5.model.Product;



public class OrderDetailRepo {
	DB_Type db = DB_Type.SQL;

	public List<OrderDetail> getAllByOrderID(int id) {
		List<OrderDetail> orders = new ArrayList<>();
		String sql = "SELECT od.orderID, od.productID, od.quantity, od.price, p.name FROM tblOrderDetail as od "
				+ "JOIN tblProduct as p "
				+ "on od.productID = p.id "
				+ " WHERE od.orderID = ?";
		try (Connection conn = DB_Connect.getConnection(db);
				PreparedStatement stmt = conn.prepareStatement(sql);
				) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				Product p = new Product();
				OrderDetail od = new OrderDetail();
				p.setId(rs.getString("productID"));
				p.setName(rs.getString("name"));
				o.setId(rs.getInt("orderID"));
				od.setOrder(o);
				od.setProduct(p);
				od.setQuantity(rs.getInt("quantity"));
				od.setPrice(rs.getInt("price"));
				orders.add(od);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return orders;
	}
	


	public OrderDetail addOrderDetail(OrderDetail order) {
		ResultSet key = null;
		String sql = "INSERT INTO tblOrderDetail(orderID, productID, quantity, price) " + "VALUES(?, ?, ?, ?)";
		try (Connection conn = DB_Connect.getConnection(db);
				PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setInt(1, order.getOrder().getId());
			stmt.setString(2, order.getProduct().getId());
			stmt.setInt(3, order.getQuantity());
			stmt.setInt(4, order.getPrice());

			int rowInserted = stmt.executeUpdate();
			if (rowInserted == 1) {		
				return order;
			} else {
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}

	}
}
