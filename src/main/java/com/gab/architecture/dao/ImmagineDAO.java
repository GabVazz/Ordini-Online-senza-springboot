package com.gab.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.gab.businesscomponent.model.Immagine;

public class ImmagineDAO extends DAOAdepter<Immagine> implements DAOConstants {

	private CachedRowSet rowSet;

	private ImmagineDAO() throws SQLException {
		rowSet = RowSetProvider.newFactory().createCachedRowSet();
	}

	public static ImmagineDAO getFactory() throws SQLException {
		return new ImmagineDAO();
	}

	public void create(Connection conn, Immagine entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_IMMAGINE);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, entity.getIdImmagine());
			rowSet.updateString(2, entity.getUrl());
			rowSet.updateString(3, entity.getDescrizione());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public void update(Connection conn, Immagine entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(UPDATE_IMMAGINE);
			// System.out.println(entity);
			ps.setString(1, entity.getUrl());
			ps.setString(2, entity.getDescrizione());
			ps.setLong(3, entity.getIdImmagine());
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public Immagine getById(Connection conn, long id) throws DAOException {
		Immagine immagine = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SELECT_IMMAGINE_BYID);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				immagine = new Immagine();
				immagine.setIdImmagine(rs.getLong(1));
				immagine.setUrl(rs.getString(2));
				immagine.setDescrizione(rs.getString(3));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return immagine;
	}

	public void delete(Connection conn, Immagine entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(DELETE_IMMAGINE);
			ps.setLong(1, entity.getIdImmagine());
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
