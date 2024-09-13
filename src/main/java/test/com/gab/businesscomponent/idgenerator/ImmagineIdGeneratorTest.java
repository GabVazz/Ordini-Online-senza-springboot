package test.com.gab.businesscomponent.idgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.gab.architecture.dao.DAOException;
import com.gab.business.idgenerator.ImmagineIdGenerator;

class ImmagineIdGeneratorTest {

	@Test
	void test() {
		try {
			ImmagineIdGenerator idGen = ImmagineIdGenerator.getInstance();
			assertNotNull(idGen);
			long id = idGen.getNextId();
			assertEquals(id, idGen.getNextId()- 1);
		} catch (DAOException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
			fail("Motivo: " + e.getMessage());
		}
	}
}
