package com.gab.businesscomponent;

import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dao.GenericReportDAO;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.businesscomponent.model.GenericReport;

public class GenericReportBC {
	private Connection conn;
	private GenericReportDAO grDAO;

	public GenericReportBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
		grDAO = GenericReportDAO.getFactory();
	}

	public Set<GenericReport> getReportData(String query, String[] columns)
			throws DAOException, ClassNotFoundException, IOException {
		try {
			return grDAO.getReportData(conn, query, columns);
		} finally {
			DBAccess.closeConnection();
		}
	}
	
	public Set<GenericReport> getReportData(String query, String[] columns, String...parametri)
			throws DAOException, ClassNotFoundException, IOException {
		try {
			return grDAO.getReportData(conn, query, columns, parametri);
		} finally {
			DBAccess.closeConnection();
		}
	}
}
