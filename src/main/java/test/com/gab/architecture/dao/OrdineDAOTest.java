package test.com.gab.architecture.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dao.OrdineDAO;
import com.gab.architecture.dao.UtenteDAO;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.businesscomponent.model.Ordine;
import com.gab.businesscomponent.model.Utente;

@TestMethodOrder(OrderAnnotation.class)
class OrdineDAOTest {
	private static Connection conn;
	private static Utente utente;
	private static Ordine ordine;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();

		utente = new Utente();
		utente.setNome("Massimo");
		utente.setCognome("Rossi");
		utente.setIndirizzo("via milano, 3");
		utente.setCap("20100");
		utente.setNascita(new GregorianCalendar(1990, 10, 1).getTime());
		utente.setUsername("gab");
		utente.setPassword("Pass01$");
		utente.setEmail("gab@tin.it");

		ordine = new Ordine();
		ordine.setIdOrdine(1);
		ordine.setTotale(3000);
		ordine.setData(new GregorianCalendar(2023, 10, 1).getTime());
		ordine.setUsername("gab");
	}

	@Test
	@Order(1)
	void testCreate() {
		try {
			UtenteDAO.getFactory().create(conn, utente);
			OrdineDAO.getFactory().create(conn, ordine);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("Create fallito: " + e.getMessage());
		}
	}

	@Test
	@Order(2)
	void testGetAll() {
		try {
			Ordine[] ordini = OrdineDAO.getFactory().getAll(conn);
			assertNotNull(ordini);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("GetALL fallito: " + e.getMessage());
		}
	}

	@Test
	@Order(3)
	void testGetById() {
		try {
			Ordine ordine2 = OrdineDAO.getFactory().getById(conn, ordine);
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("GetById fallito: " + e.getMessage());
		}
	}

	@Test
	@Order(4)
	void testUpdate() {
		try {

			ordine.setTotale(5000);
			ordine.setData(new GregorianCalendar(2022, 4, 1).getTime());
			ordine.setUsername(utente.getUsername());

			// Perform the update operation
			OrdineDAO.getFactory().update(conn, ordine);

			// Retrieve the updated record to verify the update
			Ordine updatedOrdine = OrdineDAO.getFactory().getById(conn, ordine);

			// Assertions to verify the update
			assertEquals(5000, updatedOrdine.getTotale(), 0.001);
			assertEquals(new GregorianCalendar(2022, 4, 1).getTime(), updatedOrdine.getData());
			assertEquals("gab", updatedOrdine.getUsername());
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("Update fallito: " + e.getMessage());
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws DAOException {
		try {
			System.out.println("chiusa del test in corso");
			System.out.println("provo a cancellare" + utente);
			// System.out.println(utente);
			OrdineDAO.getFactory().delete(conn, ordine);
			UtenteDAO.getFactory().delete(conn, utente);
			DBAccess.closeConnection();
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("Delete fallito: " + e.getMessage());
		}
	}
}
