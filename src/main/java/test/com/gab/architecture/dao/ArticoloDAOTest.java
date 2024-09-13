package test.com.gab.architecture.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.gab.architecture.dao.ArticoloDAO;
import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dao.ImmagineDAO;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.businesscomponent.model.Articolo;
import com.gab.businesscomponent.model.Immagine;

@TestMethodOrder(OrderAnnotation.class)
class ArticoloDAOTest {
	private static Connection conn;
	private static Articolo articolo;
	private static Immagine immagine;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();
		articolo = new Articolo();
		articolo.setIdArticolo(4);
		articolo.setMarca("Samsung");
		articolo.setModello("S20");
		articolo.setPrezzo(900);
		
		immagine = new Immagine();
		immagine.setIdImmagine(4);
		immagine.setDescrizione("ciccio");
		immagine.setUrl("url di cicico");
		
	}
	
	@Test
	@Order(1)
	void testCreate() throws SQLException {
		try {
			ArticoloDAO.getFactory().create(conn, articolo);
			ImmagineDAO.getFactory().create(conn, immagine);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("Create fallito: " + e.getMessage());
		}
	}
	
	/*
	@Test
	@Order(2)
	void testUpdateGetById() {
		try {
			articolo = new Articolo();
			articolo.setIdArticolo(4);
			articolo.setMarca("HP");
			articolo.setModello("Envy");
			articolo.setPrezzo(1200);
			ArticoloDAO.getFactory().update(conn, articolo);
			System.out.println("Aggiornato articolo");
			Articolo aRecuperato = ArticoloDAO.getFactory().getById(conn, articolo);
			System.out.println("Articolo trovato: "+ aRecuperato);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("UpdateGetById fallito: " + e.getMessage());
		}
	}
	*/
	@Test
	@Order(2)
	void testGetAll() throws DAOException {
		
		try {
			Articolo[] articoli = ArticoloDAO.getFactory().getAll(conn);
			assertNotNull(articoli);
			
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	@AfterAll
	static void tearDownAfterClass() throws SQLException {
		try {
			ImmagineDAO.getFactory().delete(conn, immagine);
			ArticoloDAO.getFactory().delete(conn, articolo);
			System.out.println("Eliminato articolo");
			DBAccess.closeConnection();
		} catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("Delete fallito: " + e.getMessage());
		}
	}
}
