package com.gab.architecture.dao;

import java.sql.Connection;

public abstract class DAOAdepter<T> implements GenericDAO<T>{

	@Override
	public void create(Connection conn, T entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Connection conn, T entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Connection conn, T entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getById(Connection conn, T entity) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] getAll(Connection conn) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
