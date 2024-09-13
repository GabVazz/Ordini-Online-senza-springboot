package test.com.gab.businesscomponent.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.gab.businesscomponent.security.Algoritmo;

class AlgoritmoTest {

	@Test
	void testConversioneMD5() {
		String pass = Algoritmo.converti("Pass01$");
		assertNotNull(pass);
		System.out.println(pass);
	}

}
