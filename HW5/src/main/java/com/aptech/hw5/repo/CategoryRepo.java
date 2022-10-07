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

public class CategoryRepo {
	DB_Type db = DB_Type.SQL;
	public List<Category> getCategories() {
		List<Category> categoryList = new ArrayList<>();
		String sql = "SELECT id, name, description FROM tblCategory";
		try (
				Connection conn = DB_Connect.getConnection(db);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				categoryList.add(c);
			}

		} catch (Exception e) {
			e.getMessage();
			return null;
		}
		return categoryList;
	}
	

	public Category getCategoryById(int id) {
		String sql = "SELECT id, name, description FROM tblCategory WHERE id = ?";
		
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				return c;
			}

		} catch (Exception e) {
			e.getMessage();
			return null;
		}
		return null;
	}

	public Category addCategory(Category c) {
		String sql = "INSERT INTO tblCategory(name, description) " + "VALUES(?, ?)";
		ResultSet key = null;
		try (Connection conn = DB_Connect.getConnection(db);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getDescription());
			int rowInserted = stmt.executeUpdate();
			if (rowInserted == 1) {
				key = stmt.getGeneratedKeys();
				key.next();
				int newKey = key.getInt(1);
				c.setId(newKey);
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

	public Category updateCategory(Category c) {
		String sql = "UPDATE tblCategory SET name = ?, " + " description = ? " + "WHERE id = ?";
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getDescription());
			stmt.setInt(3, c.getId());

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

	public Category deleteCategory(Category c) {
		String sql = "DELETE FROM tblCategory WHERE id = ?";
		try (Connection conn = DB_Connect.getConnection(db); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1,c.getId());
			int deleted = stmt.executeUpdate();
			if(deleted == 1) {
				return c;
			}
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
		return null;
	}

}
