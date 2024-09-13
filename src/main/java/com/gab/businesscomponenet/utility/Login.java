package com.gab.businesscomponenet.utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gab.architecture.dao.DAOConstants;
import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dbaccess.DBAccess;

public class Login implements DAOConstants {
	private Connection conn;

	public Login() throws ClassNotFoundException, DAOException, IOException {
		conn = DBAccess.getConnection();
	}

	public String getUserPass(String username) throws DAOException {
		try {
			PreparedStatement ps = conn.prepareStatement(SELECT_USERPASS);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	public String getAdminPass(String username) throws DAOException {
		try {
			PreparedStatement ps = conn.prepareStatement(SELECT_ADMINPASS);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
