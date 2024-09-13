package test.com.gab.architecture.dbaccess;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dbaccess.DBAccess;

class DBAccessTest {

	@Test
	void testConnection() {
		try {
			DBAccess.getConnection();
		} catch (ClassNotFoundException | DAOException | IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail("Errore nel tentativo di connessione: " + e.getMessage());
		} finally {
			try {
				DBAccess.closeConnection();
			} catch (DAOException e) {
				e.printStackTrace();
				fail("Errore nella chiusura della connessione: " + e.getMessage());
			}
		}
	}
}
