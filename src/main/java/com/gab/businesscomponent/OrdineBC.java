package com.gab.businesscomponent;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dao.OrdineDAO;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.business.idgenerator.OrdineIdGenerator;
import com.gab.businesscomponent.model.Ordine;

public class OrdineBC {
	private Connection conn;
	private OrdineDAO oDAO;
	
	public OrdineBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
		oDAO = OrdineDAO.getFactory();
	}
	
	public void create(Ordine ordine) throws DAOException, ClassNotFoundException, IOException {
		try {
			ordine.setIdOrdine(OrdineIdGenerator.getInstance().getNextId());
			ordine.setData(new Date());
			oDAO.create(conn, ordine);
		} finally {
			DBAccess.closeConnection();
		}
	}
	
	public Ordine[] getOrdini() throws DAOException {
		try {
			return oDAO.getAll(conn);
		} finally {
			DBAccess.closeConnection();
		}
	}
	
	public void update(Ordine ordine) throws DAOException, ClassNotFoundException, IOException {
		try {
			oDAO.update(conn, ordine);
		} finally {
			DBAccess.closeConnection();
		}
	}
	
	public void delete(Ordine ordine) throws DAOException {
		try {
			oDAO.delete(conn, ordine);
		} finally {
			DBAccess.closeConnection();
		}
	}
}
