package com.gab.architecture.dao;

import java.sql.SQLException;

public class DAOException extends SQLException {

	private static final long serialVersionUID = 4502393537882517982L;

	// cod errore di inserimento username/pass
	private final static int ORA1017 = 1017;
	// errore di url
	private final static int ORA17002 = 17002;
	// violazione di vincoli db
	private final static int ORA00001 = 0;

	private String message;

	public String getMessage() {
		return message;
	}

	public DAOException(SQLException sql) {
		String chiave = "";
		if (sql != null) {
			switch (sql.getErrorCode()) {
			case ORA1017:
				chiave = "Credenziali di accesso errato. Invalid Username | Password";
				log(sql);
				break;
			case ORA17002:
				chiave = "Impossibile stabilire la connessione col database. IO error di Oracle";
				log(sql);
				break;
			case ORA00001:
				chiave = "Vincolo di tabella violato";
				log(sql);
				break;
			default:
				chiave = "Eccezione SQL non prevista";
				log(sql);
			}
		}
		message = chiave;
	}

	private void log(SQLException sql) {
		sql.printStackTrace();
		System.err.println("Motivo: " + sql.getMessage());
		System.err.println("Codice: " + sql.getErrorCode());
		System.err.println("Stato app: " + sql.getSQLState());
		System.err.println("Causa: " + sql.getCause());
	}
}
