package test.com.gab.architecture.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dao.UtenteDAO;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.businesscomponent.model.Utente;

@TestMethodOrder(OrderAnnotation.class)
class UtenteDAOTest {
	private static Connection conn;
	private static Utente utente;

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
	}

	@Test
	@Order(1)
	void testCreate() {
		try {
			UtenteDAO.getFactory().create(conn, utente);
			System.out.println("Creato " + utente);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("Create fallito: " + e.getMessage());
		}
	}
	
	@Test
	@Order(2)
	void testUpdate() {
		try {
			utente.setNome("gabriele");
			utente.setCognome("vazzana");
			utente.setIndirizzo("via milano, 4");
			utente.setCap("20100");
			utente.setNascita(new GregorianCalendar(1990, 10, 1).getTime());
			utente.setUsername("max");
			utente.setPassword("Pass01$");
			utente.setEmail("gab@tin.it");
			UtenteDAO.getFactory().update(conn, utente);
			System.out.println("Aggiornato " + utente);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Update fallito: " + e.getMessage());
		}
	}
	
	
	@AfterAll
	static void tearDownAfterClass() {
		try {
			UtenteDAO.getFactory().delete(conn, utente);
			DBAccess.closeConnection();
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("Delete fallito: " + e.getMessage());
		}
	}
	
}
