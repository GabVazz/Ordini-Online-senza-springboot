package com.gab.business.idgenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gab.architecture.dao.DAOConstants;
import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dbaccess.DBAccess;

public class ImmagineIdGenerator implements IdGeneratorInterface, DAOConstants{
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private static ImmagineIdGenerator idGen;
	
	private ImmagineIdGenerator() {
		
	}

	public static ImmagineIdGenerator getInstance() {
		if(idGen == null) {
			idGen = new ImmagineIdGenerator();
		}
		return idGen;
	}

	@Override
	public long getNextId() throws DAOException, ClassNotFoundException, IOException {
		long id = 0;
		try {
			conn = DBAccess.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_IMMAGINE_SEQ);
			rs.next();
			id = rs.getLong(1);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return id;
	}
}
