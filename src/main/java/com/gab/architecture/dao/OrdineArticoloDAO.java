package com.gab.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.gab.businesscomponent.model.OrdineArticolo;

public class OrdineArticoloDAO extends DAOAdepter<OrdineArticolo> implements DAOConstants {

	private CachedRowSet rowSet;

	private OrdineArticoloDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	public static OrdineArticoloDAO getFactory() throws DAOException {
		return new OrdineArticoloDAO();
	}

	@Override
	public void create(Connection conn, OrdineArticolo entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_ORDINE_ARTICOLO);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, entity.getIdOrdine());
			rowSet.updateLong(2, entity.getIdArticolo());
			rowSet.updateDouble(3, entity.getQta());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(Connection conn, OrdineArticolo entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(DELETE_ORDINE_ARTICOLO);
			ps.setLong(1, entity.getIdOrdine());
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
