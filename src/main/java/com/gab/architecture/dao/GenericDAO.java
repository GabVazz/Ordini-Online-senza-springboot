package com.gab.architecture.dao;

import java.sql.Connection;

public interface GenericDAO<T> {
	void create(Connection conn, T entity) throws DAOException;

	void update(Connection conn, T entity) throws DAOException;

	void delete(Connection conn, T entity) throws DAOException;

	T getById(Connection conn, T entity) throws DAOException;

	T[] getAll(Connection conn) throws DAOException;
}
