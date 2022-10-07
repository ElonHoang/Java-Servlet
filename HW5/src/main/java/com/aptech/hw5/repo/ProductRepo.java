package com.aptech.hw5.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aptech.hw5.model.Category;
import com.aptech.hw5.model.Product;

public class ProductRepo {
	DB_Type db = DB_Type.SQL;

	public List<Product> getProducts() {
		List<Product> productList = new ArrayList<>();
		String sql = "SELECT p.id, p.cat_id, p.name, p.price, p.image, p.description, c.id, c.name, c.description FROM tblProduct as p JOIN tblCategory as c on c.id = p.cat_id "
				+ "WHERE 1=1";

		try (Connection conn = DB_Connect.getConnection(db);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				Product p = new Product();
				Category c = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description"));

				p.setId(rs.getString("id"));
				p.setCategory(c);
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setImage(rs.getString("image"));
				p.setDescription(rs.getString("description"));
				productList.add(p);
			}

		} catch (Exception e) {
			e.getMessage();
			return null;
		}
		return productList;
	}

	public int countProducts() {
		String sql = "SELECT COUNT(*) FROM tblProduct WHERE 1=1";
		try (Connection con = DB_Connect.getConnection(db);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.getMessage();
			return 0;
		}
		return 0;
	}

	public List<Product> pagingProducts(int offset, int limit) {
		List<Product> products = new ArrayList<>();
		String sql = "SELECT p.id, p.cat_id, p.name, p.price, p.image, p.description, c.id, c.name, c.description FROM tblProduct as p JOIN tblCategory as c on c.id = p.cat_id "
				+ "WHERE 1=1" + " LIMIT ?, ? ";
		try (Connection con = DB_Connect.getConnection(db); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setInt(1, (offset - 1) * limit);
			stmt.setInt(2, limit);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				Category c = new Category(rs.getInt("cat_id"), rs.getString("c.name"), rs.getString("description"));
				p.setId(rs.getString("id"));
				p.setCategory(c);
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setImage(rs.getString("image"));
				p.setDescription(rs.getString("description"));

				products.add(p);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return null;
		}
		return products;

	}

	public List<Product> searchByName(String name, int offset, int limit) {
		String sql = "SELECT id, cat_id, name, price, image, description FROM tblProduct WHERE name LIKE ? LIMIT ?,? ";
		List<Product> products = new ArrayList<>();
		try (Connection conn = DB_Connect.getConnection(db);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, '%'+ name +'%');
			stmt.setInt(2, (offset-1) * limit);
			stmt.setInt(3, limit);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				Category c = new Category();
				p.setId(rs.getString("id"));
				c.setId(rs.getInt("cat_id"));
				p.setCategory(c);
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setDescription(rs.getString("description"));
				p.setImage(rs.getString("image"));
				products.add(p);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return products;
	}
	public int countSearchByName(String name) {
		String sql = "SELECT COUNT(*) FROM tblProduct WHERE name LIKE ?";
		try(Connection conn = DB_Connect.getConnection(db);
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setString(1, '%' + name + '%');
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return (int)rs.getInt(1);
			}		
			
		}catch(Exception ex) {
			ex.getMessage();
			return 0;
		}
		return 0;
	}

	public Product getProductById(String id) {
		String sql = "SELECT p.id, p.cat_id, p.name, p.price, p.image, p.description, c.id, c.name, c.description FROM tblProduct as p JOIN tblCategory as c "
				+ "on p.cat_id = c.id" + " WHERE p.id = ?";
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Product p = new Product();
				Category c = new Category(rs.getInt("cat_id"), rs.getString("c.name"), rs.getString("description"));
				p.setId(rs.getString("id"));
				p.setCategory(c);
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setImage(rs.getString("image"));
				p.setDescription(rs.getString("description"));
				return p;
			}

		} catch (Exception e) {
			e.getMessage();
			return null;
		}
		return null;
	}

	public Product addProduct(Product c) {
		String sql = "INSERT INTO tblProduct(id, cat_id, name, price, image, description) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		ResultSet key = null;
		try (Connection conn = DB_Connect.getConnection(db);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, c.getId());
			stmt.setInt(2, c.getCategory().getId());
			stmt.setString(3, c.getName());
			stmt.setDouble(4, c.getPrice());
			stmt.setString(5, c.getImage());
			stmt.setString(6, c.getDescription());

			System.out.println(stmt);

			int rowInserted = stmt.executeUpdate();
			if (rowInserted == 1) {
				return c;
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

	public Product updateProduct(Product c) {
		String sql = "UPDATE tblProduct SET cat_id = ?, " + "name = ?, " + "price = ?, " + "description = ?, "
				+ "image = ? " + "WHERE id = ?";
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, c.getCategory().getId());
			stmt.setString(2, c.getName());
			stmt.setDouble(3, c.getPrice());
			stmt.setString(4, c.getDescription());
			stmt.setString(5, c.getImage());
			stmt.setString(6, c.getId());

			int rowUpdated = stmt.executeUpdate();
			if (rowUpdated == 1) {
				return c;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Product deleteProduct(Product c) {
		String sql = "DELETE FROM tblProduct WHERE id IN (?)";
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, c.getId());
			int deleted = stmt.executeUpdate();
			if (deleted == 1) {
				return c;
			}
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
		return null;
	}

}
