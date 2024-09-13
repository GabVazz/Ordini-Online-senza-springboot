package com.gab.businesscomponent.facade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import com.gab.architecture.dao.DAOException;
import com.gab.businesscomponent.ArticoloBC;
import com.gab.businesscomponent.GenericReportBC;
import com.gab.businesscomponent.ImmagineBC;
import com.gab.businesscomponent.OrdineBC;
import com.gab.businesscomponent.model.Articolo;
import com.gab.businesscomponent.model.GenericReport;
import com.gab.businesscomponent.model.Immagine;
import com.gab.businesscomponent.model.Ordine;

public class AdminFacade {
	private static AdminFacade cF;
	private OrdineBC oBC;
	private ArticoloBC aBC;
	private ImmagineBC iBC;
	private GenericReportBC grBC;

	private AdminFacade() {

	}

	public static AdminFacade getIstance() {
		if (cF != null) {
			return cF;
		}
		return new AdminFacade();
	}

	public Ordine[] getOrdini() throws DAOException, ClassNotFoundException, IOException {
		oBC = new OrdineBC();
		return oBC.getOrdini();
	}

	public void delete(Ordine ordine) throws DAOException, ClassNotFoundException, IOException {
		oBC = new OrdineBC();
		oBC.delete(ordine);
	}

	public Articolo[] getArticoli() throws DAOException, ClassNotFoundException, IOException {
		aBC = new ArticoloBC();
		return aBC.getArticoli();
	}

	public void createOrUpdate(Articolo articolo) throws DAOException, ClassNotFoundException, IOException {
		aBC = new ArticoloBC();
		aBC.createOrUpdate(articolo);
	}

	public void delete(Articolo articolo) throws DAOException, ClassNotFoundException, IOException {
		aBC = new ArticoloBC();
		aBC.delete(articolo);
	}

	public Set<Articolo> cercaArticolo(String query) throws DAOException, ClassNotFoundException, IOException {
		aBC = new ArticoloBC();
		return aBC.cercaArticolo(query);
	}

	public Articolo findArticoloById(Articolo articolo) throws DAOException, ClassNotFoundException, IOException {
		aBC = new ArticoloBC();
		return aBC.findById(articolo);
	}
	
	public Articolo findArticoloById(long id) throws DAOException, ClassNotFoundException, IOException {
		aBC = new ArticoloBC();
		return aBC.findById(id);
	}

	public Immagine findImmagineById(long id) throws ClassNotFoundException, IOException, SQLException {
		iBC = new ImmagineBC();
		return iBC.findById(id);
	}
	
	public Set<GenericReport> getReportData(String query, String[] columns) throws DAOException, ClassNotFoundException, IOException {
		grBC = new GenericReportBC();
		return grBC.getReportData(query, columns);
	}
	
	public Set<GenericReport> getReportData(String query, String[] columns, String...parametri) throws DAOException, ClassNotFoundException, IOException {
		grBC = new GenericReportBC();
		return grBC.getReportData(query, columns, parametri);
	}
}
