package test.com.gab.architecture.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dao.ImmagineDAO;
import com.gab.architecture.dbaccess.DBAccess;
import com.gab.business.idgenerator.ImmagineIdGenerator;
import com.gab.businesscomponent.model.Immagine;

@TestMethodOrder(OrderAnnotation.class)
class ImmagineDAOTest {
	private static Connection conn;
	private static Immagine immagine;

	@BeforeAll
    static void setUpBeforeClass() throws Exception {
        conn = DBAccess.getConnection();
        immagine = new Immagine();

        //bisogna creare anche un articolo
        immagine.setIdImmagine(ImmagineIdGenerator.getInstance().getNextId());
        immagine.setUrl("http/prova.it");
        immagine.setDescrizione("descrizione");
        System.out.println("Creato: " + immagine);
    }

    @Test
    @Order(1)
    void testCreate() throws SQLException {
        try {
            ImmagineDAO.getFactory().create(conn, immagine);
            System.out.println("Creazione riuscita: " + immagine);
        } catch (DAOException e) {
            e.printStackTrace();
            fail("Creazione fallita: " + e.getMessage());
        }
    }

    @Test
    @Order(2)
    void testGetById() throws SQLException {
        try {
            Immagine retrievedImmagine = ImmagineDAO.getFactory().getById(conn, immagine.getIdImmagine());
            assertEquals(immagine.getIdImmagine(), retrievedImmagine.getIdImmagine());
            assertEquals(immagine.getUrl(), retrievedImmagine.getUrl());
            assertEquals(immagine.getDescrizione(), retrievedImmagine.getDescrizione());
        } catch (DAOException e) {
            e.printStackTrace();
            fail("Recupero fallito: " + e.getMessage());
        }
    }

    @Test
    @Order(3)
    void testUpdate() throws SQLException {
        try {
            immagine.setUrl("https/prova2");
            immagine.setDescrizione("descrizione2");

            // Perform the update operation
            ImmagineDAO.getFactory().update(conn, immagine);

            // Retrieve the updated record to verify the update
            Immagine updatedImmagine = ImmagineDAO.getFactory().getById(conn, immagine.getIdImmagine());

            // Assertions to verify the update
            assertEquals("descrizione2", updatedImmagine.getDescrizione());
            assertEquals("https/prova2", updatedImmagine.getUrl());
        } catch (DAOException e) {
            e.printStackTrace();
            fail("Aggiornamento fallito: " + e.getMessage());
        }
    }

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("chiusura del test in corso");
		System.out.println("provo a cancellare " + immagine);
		// System.out.println(utente);
		ImmagineDAO.getFactory().delete(conn, immagine);
		DBAccess.closeConnection();
	}
}
