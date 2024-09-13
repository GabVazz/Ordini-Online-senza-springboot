package com.gab.architecture.dao;

public interface DAOConstants {
	/*------------------------------Client*/
	String SELECT_UTENTE = "Select * from utente";
	String SELECT_ORDINE = "Select * from ordine";
	String SELECT_ARTICOLO = "Select * from articolo";
	String SELECT_ORDINE_ARTICOLO = "Select * from ordine_articolo";
	String SELECT_IMMAGINE = "Select * from immagine";
	String SELECT_USERPASS = "Select password from utente where username = ?";
	
	String DELETE_UTENTE = "Delete from utente where username = ?";
	
	String UPDATE_UTENTE = "Update utente set nome = ?, cognome = ?, indirizzo = ?, cap = ?, nascita = ?, password = ?, email = ? where username = ?";
	String UPDATE_ORDINE = "Update ordine set totale = ?, data = ?, username = ? where id_ordine = ?";
	String UPDATE_IMMAGINE = "Update immagine set url = ?, descrizione = ? where id_immagine = ?";
	
	/*------------------------------Sequence*/
	String SELECT_ORDINE_SEQ = "Select ordine_seq.nextval from dual";
	String SELECT_ARTICOLO_SEQ = "Select ordine_seq.nextval from dual";
	String SELECT_IMMAGINE_SEQ = "Select immagine_seq.nextval from dual";

	/*------------------------------Admin*/
	String SELECT_ADMIN = "Select * from amministratore";
	String SELECT_REPORT = "Select * from report";
	String SELECT_INFO_ARTICOLO_UTENTE = "Select * from info_articolo_utente";
	String SELECT_SPESA_UTENTE_IN_DATO_PERIODO = "SELECT u.username, u.email, SUM(o.totale) AS totale_speso\r\n"
			+ "FROM utente u\r\n"
			+ "JOIN ordine o ON u.username = o.username\r\n"
			+ "WHERE o.data BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?', 'YYYY-MM-DD')\r\n"
			+ "GROUP BY u.username, u.email\r\n"
			+ "HAVING SUM(o.totale) > ?\r\n"
			+ "ORDER BY totale_speso DESC";
	String SELECT_ARTICOLI_PIU_VENDUTI = "Select * from articoli_piu_venduti";
	String SELECT_ADMINPASS = "Select password from amministratore where username = ?";
	String UPDATE_ARTICOLO = "Update articolo set marca = ?, modello = ?, prezzo = ? where id_articolo = ?";
	String DELETE_ARTICOLO = "Delete from articolo where id_articolo = ?";
	String SELECT_ORDINE_BYID = "Select * from ordine where id_ordine = ?";
	String SELECT_ARTICOLO_BYID = "Select * from articolo where id_articolo = ?";
	String SELECT_IMMAGINE_BYID = "Select * from immagine where id_immagine = ?";
	String DELETE_IMMAGINE = "Delete from immagine where id_immagine = ?";
	String DELETE_ORDINE = "Delete from ordine where id_ordine = ?";
	String DELETE_ORDINE_ARTICOLO = "Delete from ordine_articolo where id_ordine = ?";
}
