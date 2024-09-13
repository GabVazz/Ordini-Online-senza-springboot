package com.gab.businesscomponent;

import java.io.IOException;
import java.sql.Connection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.gab.architecture.dao.ArticoloDAO;
import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.business.idgenerator.ArticoloIdGenerator;
import com.gab.businesscomponent.model.Articolo;

public class ArticoloBC {
	private Connection conn;
	private ArticoloDAO aDAO;

	public ArticoloBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
		aDAO = ArticoloDAO.getFactory();
	}

	public void createOrUpdate(Articolo articolo) throws DAOException, ClassNotFoundException, IOException {
		try {
			if (articolo.getIdArticolo() > 0) {
				aDAO.update(conn, articolo);
			} else {
				articolo.setIdArticolo(ArticoloIdGenerator.getInstance().getNextId());
				aDAO.create(conn, articolo);
			}
		} finally {
			DBAccess.closeConnection();
		}
	}

	public Articolo findById(Articolo articolo) throws DAOException {
		try {
			return aDAO.getById(conn, articolo);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public Articolo findById(long id) throws DAOException {
		try {
			return aDAO.getById(conn, id);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public Articolo[] getArticoli() throws DAOException {
		try {
			return aDAO.getAll(conn);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public void delete(Articolo articolo) throws DAOException {
		try {
			aDAO.delete(conn, articolo);
		} finally {
			DBAccess.closeConnection();
		}
	}

	public Set<Articolo> cercaArticolo(String query) throws DAOException {
		System.out.println("bc cerca articolo");
		try {
			Set<Articolo> set = new TreeSet<Articolo>(new Comparator<Articolo>() {

				@Override
				public int compare(Articolo a1, Articolo a2) {
					return (int) (a1.getIdArticolo() - a2.getIdArticolo());
				}

			});
			//System.out.println("query " + query);
			String[] criterioRicerca = query.toLowerCase().split(" ");
			for (int i = 0; i < criterioRicerca.length; i++) {
				criterioRicerca[i] = criterioRicerca[i].replaceAll("\\s", "");
			}
			System.out.println(criterioRicerca.toString());
			for (Articolo a : getArticoli()) {
				for (String c : criterioRicerca) {
					if (a.getMarca().toLowerCase().replaceAll("\\s", "").contains(c)
							|| a.getModello().toLowerCase().replaceAll("\\s", "").contains(c)) {
						set.add(a);
						//System.out.println("trovato");
					}
				}
			}
			return set;
		} finally {
			DBAccess.closeConnection();
		}
	}
}
