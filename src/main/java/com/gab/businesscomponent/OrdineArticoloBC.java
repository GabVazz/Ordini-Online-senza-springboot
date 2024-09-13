package com.gab.businesscomponent;

import java.io.IOException;
import java.sql.Connection;

import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dao.OrdineArticoloDAO;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.businesscomponent.model.OrdineArticolo;

public class OrdineArticoloBC {
	private Connection conn;
	private OrdineArticoloDAO oaDAO;
	
	public OrdineArticoloBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
		oaDAO = OrdineArticoloDAO.getFactory();
	}
	
	public void create(OrdineArticolo ordineArticolo) throws DAOException, ClassNotFoundException, IOException {
		try {
			oaDAO.create(conn, ordineArticolo);
		} finally {
			DBAccess.closeConnection();
		}
	}
	
	public void delete(OrdineArticolo ordineArticolo) throws DAOException {
		try {
			oaDAO.delete(conn, ordineArticolo);
		} finally {
			DBAccess.closeConnection();
		}
	}
}
