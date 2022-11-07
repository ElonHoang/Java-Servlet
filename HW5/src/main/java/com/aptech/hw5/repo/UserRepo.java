package com.aptech.hw5.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aptech.hw5.model.User;


public class UserRepo {
	DB_Type db = DB_Type.SQL;

	public boolean checkLogin(String user, String pass) {
		String sql = "SELECT COUNT(1), user FROM tblUser WHERE user = ? AND pass = ?";
		try (Connection con = DB_Connect.getConnection(db); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, user);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int row = rs.getInt(1);
				String usr = rs.getString("user");
				if (row > 0 && usr.equals(user))
					return true;
			}
		} catch (Exception ex) {
			ex.getMessage();
			return false;
		}
		return false;
	}

	public User createAccount(User u) {
		ResultSet key = null;
		String sql = "INSERT INTO tblUser(user, pass) " + "VALUES (?, ?)";
		try (Connection conn = DB_Connect.getConnection(db);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, u.getUser());
			stmt.setString(2, u.getPass());
			int rowInserted = stmt.executeUpdate();
			if (rowInserted == 1) {
				return u;
			} else {
				return null;
			}

		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
}
