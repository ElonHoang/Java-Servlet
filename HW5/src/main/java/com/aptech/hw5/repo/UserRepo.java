package com.aptech.hw5.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepo {
	DB_Type db = DB_Type.SQL;

	public boolean checkLogin(String user, String pass) {
		String sql = "SELECT COUNT(1) FROM tblUser WHERE user = ? AND pass = ?";
		try (Connection con = DB_Connect.getConnection(db); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, user);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int row = rs.getInt(1);
				if(row > 0)
				return true;
			}
		} catch (Exception ex) {
			ex.getMessage();
			return false;
		}
		return false;
	}
}
