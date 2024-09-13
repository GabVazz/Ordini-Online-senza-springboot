package test.com.gab.architecture.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dao.OrdineArticoloDAO;
import com.gab.architecture.dao.OrdineDAO;
import com.gab.architecture.dao.UtenteDAO;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.businesscomponent.model.Ordine;
import com.gab.businesscomponent.model.OrdineArticolo;
import com.gab.businesscomponent.model.Utente;

class OrdineArticoloDAOTest {
	private static Connection conn;
	private static Ordine ordine;
	private static Utente utente;
	private static OrdineArticolo ordineArticolo;
	// private static Articolo articolo;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();

		utente = new Utente();
		utente.setNome("Massimo");
		utente.setCognome("Rossi");
		utente.setIndirizzo("via milano, 3");
		utente.setCap("20100");
		utente.setNascita(new GregorianCalendar(1990, 10, 1).getTime());
		utente.setUsername("max");
		utente.setPassword("Pass01$");
		utente.setEmail("gab@tin.it");

		ordine = new Ordine();
		ordine.setIdOrdine(1);
		ordine.setTotale(3000);
		ordine.setData(new GregorianCalendar(2023, 10, 1).getTime());
		ordine.setUsername("max");

		ordineArticolo = new OrdineArticolo();
		ordineArticolo.setIdOrdine(1);
		ordineArticolo.setIdArticolo(3);
		ordineArticolo.setQta(4);

	}
	
	@Test
	void testCreate() {
		try {
			UtenteDAO.getFactory().create(conn, utente);
			OrdineDAO.getFactory().create(conn, ordine);
			OrdineArticoloDAO.getFactory().create(conn, ordineArticolo);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Create fallito: " + e.getMessage());
		}
	}

	@AfterAll
	static void tearDownAfterClass() {
		try {
			UtenteDAO.getFactory().delete(conn, utente);
			OrdineDAO.getFactory().delete(conn, ordine);
			System.out.println("Eliminato ordine, utente e ordine_articolo");
			DBAccess.closeConnection();
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("Delete fallito: " + e.getMessage());
		}
	}
}
