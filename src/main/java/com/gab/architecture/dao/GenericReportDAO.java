package com.gab.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.gab.businesscomponent.model.GenericReport;

public class GenericReportDAO {
	private GenericReportDAO() throws DAOException {

	}

	public static GenericReportDAO getFactory() throws DAOException {
		return new GenericReportDAO();
	}

	public Set<GenericReport> getReportData(Connection conn, String query, String[] columns) throws DAOException {
		Set<GenericReport> reports = new HashSet<GenericReport>();

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				GenericReport report = new GenericReport();
				for (String column : columns) {
					report.addColumn(column, resultSet.getObject(column));
				}
				reports.add(report);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return reports;
	}

	public Set<GenericReport> getReportData(Connection conn, String query, String[] columns, String... parametri)
			throws DAOException {
		Set<GenericReport> reports = new HashSet<GenericReport>();

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			/*
			for (int i = 0; i < parametri.length; i++) {
				System.out.println("Colonna: " +(i+1)+ " e parametro: "  + parametri[i]);
				ps
				ps.setObject((i + 1), parametri[i]);
			}
			*/
			ps.setString(1, parametri[1]);
			ps.setString(2, parametri[2]);
			ps.setInt(3, Integer.parseInt(parametri[3]));
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				GenericReport report = new GenericReport();
				for (String column : columns) {
					report.addColumn(column, resultSet.getObject(column));
				}
				reports.add(report);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return reports;
	}
}
