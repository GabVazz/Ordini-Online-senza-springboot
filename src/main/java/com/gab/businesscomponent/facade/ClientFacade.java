package com.gab.businesscomponent.facade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import com.gab.architecture.dao.DAOException;
import com.gab.businesscomponent.ArticoloBC;
import com.gab.businesscomponent.ImmagineBC;
import com.gab.businesscomponent.OrdineArticoloBC;
import com.gab.businesscomponent.OrdineBC;
import com.gab.businesscomponent.UtenteBC;
import com.gab.businesscomponent.model.Articolo;
import com.gab.businesscomponent.model.Immagine;
import com.gab.businesscomponent.model.Ordine;
import com.gab.businesscomponent.model.OrdineArticolo;
import com.gab.businesscomponent.model.Utente;

public class ClientFacade {
	private static ClientFacade cF;
	private UtenteBC uBC;
	private OrdineBC oBC;
	private ArticoloBC aBC;
	private OrdineArticoloBC oaBC;
	private ImmagineBC iBC;

	private ClientFacade() {

	}

	public static ClientFacade getIstance() {
		if (cF != null) {
			return cF;
		}
		return new ClientFacade();
	}

	public void create(Utente utente) throws DAOException, ClassNotFoundException, IOException {
		uBC = new UtenteBC();
		uBC.create(utente);
	}

	public void create(Ordine ordine) throws DAOException, ClassNotFoundException, IOException {
		oBC = new OrdineBC();
		oBC.create(ordine);
	}

	public void create(OrdineArticolo oa) throws DAOException, ClassNotFoundException, IOException {
		oaBC = new OrdineArticoloBC();
		oaBC.create(oa);
	}

	public Articolo[] getArticoli() throws DAOException, ClassNotFoundException, IOException {
		aBC = new ArticoloBC();
		return aBC.getArticoli();
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
}
