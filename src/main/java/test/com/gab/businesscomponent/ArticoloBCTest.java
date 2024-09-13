package test.com.gab.businesscomponent;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gab.architecture.dao.DAOException;
import com.gab.businesscomponent.ArticoloBC;
import com.gab.businesscomponent.model.Articolo;

class ArticoloBCTest {
	private static String query;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		query = "select * from articolo where modello = Mac Apple";
	}

	@Test
	void testCercaArticolo() throws DAOException, ClassNotFoundException, IOException {
		System.out.println("cerco articolo");
		ArticoloBC aBC= new ArticoloBC();
		Set<Articolo> articoli = aBC.cercaArticolo(query);
		System.out.println(articoli);
		assertNotNull(articoli);
	}
}
