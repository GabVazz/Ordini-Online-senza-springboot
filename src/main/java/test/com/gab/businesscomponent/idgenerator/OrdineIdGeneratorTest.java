package test.com.gab.businesscomponent.idgenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.gab.architecture.dao.DAOException;
import com.gab.business.idgenerator.OrdineIdGenerator;

class OrdineIdGeneratorTest {

	@Test
	void test() {
		try {
			OrdineIdGenerator idGen = OrdineIdGenerator.getInstance();
			assertNotNull(idGen);
			long id = idGen.getNextId();
			assertEquals(id, idGen.getNextId()- 1);
		} catch (DAOException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail("Motivo: " + e.getMessage());
		}
	}
}
