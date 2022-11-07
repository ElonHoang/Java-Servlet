package com.aptech.hw5.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aptech.hw5.model.Order;
import com.aptech.hw5.model.Product;
import com.aptech.hw5.model.User;
import com.aptech.hw5.model.ulti.Ultility;

public class OrderRepo {
	DB_Type db = DB_Type.SQL;

	public List<Order> getAll(User user) {
		List<Order> orders = new ArrayList<>();
		String sql = "SELECT id, user, total_price, datetime FROM tblOrder WHERE user = ?";
		try (Connection conn = DB_Connect.getConnection(db);
				PreparedStatement stmt = conn.prepareStatement(sql);
				) {
			stmt.setString(1, user.getUser());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				User u = new User();
				o.setId(rs.getInt("id"));
				u.setUser(rs.getString("user"));
				o.setUser(u);
				o.setTotal_price(rs.getInt("total_price"));
				o.setDatetime(Ultility.convertLocalTimestampToLocalDateTime(rs.getTimestamp("datetime")));
				orders.add(o);
			}

		} catch (Exception ex) {
			System.out.println("Order Repo");
			System.out.println(ex.getMessage());
			return null;
		}
		return orders;
	}

	public Order addOrder(Order order) {
		ResultSet key = null;
		String sql = "INSERT INTO tblOrder(user, total_price, datetime) " + "VALUES(?, ?, ?)";
		try (Connection conn = DB_Connect.getConnection(db); 
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			
			stmt.setString(1, order.getUser().getUser());
			stmt.setInt(2, order.getTotal_price());
			stmt.setTimestamp(3,Ultility.convertLocalDatetimeToTimestamp(order.getDatetime()));
			
			int rowInserted = stmt.executeUpdate();
			if (rowInserted == 1) {
				key = stmt.getGeneratedKeys();
				key.next();
				int newKey = key.getInt(1);
				order.setId(newKey);
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
