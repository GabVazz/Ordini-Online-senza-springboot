package com.gab.businesscomponent;

import java.io.IOException;
import java.sql.Connection;

import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dao.UtenteDAO;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.businesscomponent.model.Utente;

public class UtenteBC {
	private Connection conn;
	private UtenteDAO uDAO;
	
	public UtenteBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
		uDAO = UtenteDAO.getFactory();
	}
	
	public void create(Utente utente) throws DAOException {
		try {
			uDAO.create(conn,utente);
		} finally {
			DBAccess.closeConnection();
		}
	}
	
	public void delete(Utente utente) throws DAOException {
		try {
			uDAO.delete(conn, utente);
		} finally {
			DBAccess.closeConnection();
		}
	}
	
	public void update(Utente utente) throws DAOException {
		try {
			uDAO.update(conn, utente);
		} finally {
			DBAccess.closeConnection();
		}
	}
}
