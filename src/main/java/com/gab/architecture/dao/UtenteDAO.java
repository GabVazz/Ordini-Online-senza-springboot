package com.gab.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.gab.businesscomponent.model.Utente;
import com.gab.businesscomponent.security.Algoritmo;

public class UtenteDAO extends DAOAdepter<Utente> implements DAOConstants{

	private CachedRowSet rowSet;
	
	private UtenteDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	public static UtenteDAO getFactory() throws DAOException {
		return new UtenteDAO();
	}
	
	@Override
	public void create(Connection conn, Utente entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_UTENTE);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateString(1, entity.getNome());
			rowSet.updateString(2, entity.getCognome());
			rowSet.updateString(3, entity.getIndirizzo());
			rowSet.updateString(4, entity.getCap());
			rowSet.updateDate(5, new java.sql.Date(entity.getNascita().getTime()));
			rowSet.updateString(6, entity.getUsername());
			rowSet.updateString(7, Algoritmo.converti(entity.getPassword()));
			rowSet.updateString(8, entity.getEmail());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
		} catch(SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(Connection conn, Utente entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(DELETE_UTENTE);
			ps.setString(1, entity.getUsername());
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public void update(Connection conn, Utente entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(UPDATE_UTENTE);
			//System.out.println(entity);
			ps.setString(1, entity.getNome());
			ps.setString(2, entity.getCognome());
			ps.setString(3, entity.getIndirizzo());
			ps.setString(4, entity.getCap());
			ps.setDate(5, new java.sql.Date(entity.getNascita().getTime()));
			ps.setString(6, entity.getPassword());
			ps.setString(7, entity.getEmail());
			ps.setString(8, entity.getUsername());
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
