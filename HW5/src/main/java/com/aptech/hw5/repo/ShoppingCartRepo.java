package com.aptech.hw5.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aptech.hw5.model.Product;
import com.aptech.hw5.model.ShoppingCart;
import com.aptech.hw5.model.User;

public class ShoppingCartRepo {
	DB_Type db = DB_Type.SQL;

	public int getTotalCartPrice(List<ShoppingCart> cart, String[] check) {
		int sum = 0;
		ResultSet rs = null;
		String sql = "SELECT price FROM tblProduct WHERE id = ? ";
		try {
			Connection conn = DB_Connect.getConnection(db);
			if (cart.size() == 0) {
				return 0;
			}

			if (check == null || check.length == 0) {
				for (ShoppingCart p : cart) {
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, p.getProduct().getId());
					rs = stmt.executeQuery();

					while (rs.next()) {
						sum += rs.getDouble("price") * p.getQuantity();
					}
				}
			} else {
				for (ShoppingCart p : cart) {
					for (String c : check) {
						if (p.getProduct().getId().equalsIgnoreCase(c)) {
							PreparedStatement stmt = conn.prepareStatement(sql);
							stmt.setString(1, c);
							rs = stmt.executeQuery();

							while (rs.next()) {
								sum += rs.getDouble("price") * p.getQuantity();
							}
						}
					}
				}
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return sum;

	}

	public int getQuantityByProductID(String product) {
		int quantity = 0;
		String sql = "SELECT quantity FROM tblShoppingCart WHERE productID = ?";
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, product);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				quantity = rs.getInt("quantity");
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return 0;
		}
		return quantity;
	}

	public String getProductIDByProductID(String product) {
		String sql = "SELECT productID FROM tblShoppingCart WHERE productID = ?";
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, product);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getString("ProductID");
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return null;
	}

	public String updateQuantityInCart(String idProduct, int quantity) {
		String sql = "UPDATE tblShoppingCart SET quantity = ? WHERE productID = ?";
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, quantity);
			stmt.setString(2, idProduct);
			int rowUpdated = stmt.executeUpdate();
			if (rowUpdated == 1) {
				return idProduct;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;

	}

	public ShoppingCart addCart(Product p, User u, int quantity) {
		ResultSet key = null;
		String sql = "INSERT INTO tblShoppingCart (user, productID, quantity, total_price)" + " VALUES(?, ?, ?, ?)";
		try (Connection conn = DB_Connect.getConnection(db);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, u.getUser());
			stmt.setString(2, p.getId());
			stmt.setInt(3, quantity);
			stmt.setDouble(4, p.getPrice() * quantity);
			int rowInserted = stmt.executeUpdate();
			ShoppingCart sc = new ShoppingCart();
			if (rowInserted == 1) {
				key = stmt.getGeneratedKeys();
				key.next();
				int newKey = key.getInt(1);
				sc.setId(newKey);
				return sc;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.getMessage();
			return null;
		} finally {
			try {
				if (key != null)
					key.close();
			} catch (SQLException ex) {
				ex.getMessage();

			}
		}
	}

	public List<ShoppingCart> getAllCart(User username) {
		List<ShoppingCart> carts = new ArrayList<>();
		String sql = "SELECT s.id, s.user, s.productID, s.quantity, s.total_price, p.name, p.price FROM tblShoppingCart as s "
				+ " JOIN tblProduct as p on s.productID = p.id " + " WHERE s.user = ? ;";
		try (Connection conn = DB_Connect.getConnection(db);
				PreparedStatement stmt = conn.prepareStatement(sql);
				) {
			stmt.setString(1, username.getUser());
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ShoppingCart sc = new ShoppingCart();
				Product p = new Product();
				User s = new User();
				s.setUser(rs.getString("user"));
				p.setId(rs.getString("productID"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				sc.setProduct(p);
				sc.setId(rs.getInt("id"));
				sc.setUser(s);
				sc.setQuantity(rs.getInt("quantity"));
				sc.setTotal_price(rs.getDouble("total_price"));
				carts.add(sc);
			}
			System.out.println(carts);

		} catch (Exception ex) {
			System.out.println("SC Repo");
			System.out.println(ex.getMessage());
		}
		return carts;
	}

	public ShoppingCart getCartById(ShoppingCart cart) {
		String sql = "SELECT id, user, productID, quantity, total_price FROM tblShoppingCart WHERE id = ?";
		ShoppingCart sc = new ShoppingCart();
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, cart.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("productID"));
				User s = new User();
				s.setUser(rs.getString("user"));
				sc.setProduct(p);
				sc.setUser(s);
				sc.setQuantity(rs.getInt("quantity"));
				sc.setTotal_price(rs.getDouble("total_price"));
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return sc;
	}

	public boolean deleteCart(int id) {
		String sql = "DELETE FROM tblShoppingCart WHERE id IN (?)";
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, id);
			int rowDeleted = stmt.executeUpdate();
			if (rowDeleted == 1) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}
	
	public boolean deleteCartByProductID(String id) {
		String sql = "DELETE FROM tblShoppingCart WHERE productID IN (?)";
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, id);
			int rowDeleted = stmt.executeUpdate();
			if (rowDeleted == 1) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}
}
