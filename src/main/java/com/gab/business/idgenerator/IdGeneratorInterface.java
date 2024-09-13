package com.gab.business.idgenerator;
import java.io.IOException;

import com.gab.architecture.dao.DAOException;

public interface IdGeneratorInterface {
	long getNextId() throws DAOException, ClassNotFoundException, IOException;
}
