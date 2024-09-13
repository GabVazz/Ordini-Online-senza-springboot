package com.gab.businesscomponent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dao.ImmagineDAO;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.businesscomponent.model.Immagine;

public class ImmagineBC {
	private Connection conn;
	private ImmagineDAO iDAO;

	public ImmagineBC() throws ClassNotFoundException, IOException, SQLException {
		conn = DBAccess.getConnection();
		iDAO = ImmagineDAO.getFactory();
	}

	public void delete(Immagine immagine) throws DAOException {
		try {
			iDAO.delete(conn, immagine);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public Immagine findById(long id) throws DAOException {
		try {
			return iDAO.getById(conn, id);
		} finally {
			DBAccess.closeConnection();
		}
	}
}
