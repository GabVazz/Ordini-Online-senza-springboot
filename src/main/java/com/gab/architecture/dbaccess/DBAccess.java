package com.gab.architecture.dbaccess;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.gab.architecture.dao.DAOConstants;
import com.gab.architecture.dao.DAOException;

public class DBAccess implements DAOConstants {
	private static Connection conn;

	public synchronized static Connection getConnection() throws ClassNotFoundException, DAOException, IOException {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("properties/config.properties");
			Properties p = new Properties();
			p.load(input);

			Class.forName(p.getProperty("jdbcDriver"));
			conn = DriverManager.getConnection(p.getProperty("jdbcURL"), p.getProperty("jdbcUsername"),
					p.getProperty("jdbcPass"));
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public static void closeConnection() throws DAOException {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
