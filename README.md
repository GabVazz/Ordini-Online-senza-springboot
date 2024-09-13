# OrdiniOnline_senza_springboot
Applicazione web enterprise senza framework che espone una serie di articoli all’utente e quest’ultimo può aggiungerli al carello e procedere con l’ordine.

- Progetto monolitico interamente basato su java EE 8 e jsp
- Database: Oracle SQL 19
- Front end: html 5, css, bootstrap 3.0
- Server: Tomcat 9
- Test: JUnit 5

## Dettagli implementazioni:

- Implementazione di ogni model per rappresentare gli insieme di dati
- Creazione di script sql che creano a cascata tutta la struttura fisica del db incluse tabelle e le loro relazioni, sequence e vincoli
- Implementazione classe “DBAccess” che si preoccupa di creare una connessione “synchronized” col db restituendo un oggetto di tipo “Connection” e analogamente un metodo “closeConnection()” che si preoccupa di chiuderla. I parametri di connessioni vengono letti da un ClassLoader che, leggendo dal thread corrente il contesto dell’applicazione, memorizza i dati del file properties contenti i parametri di connessione per il DB incluso il driver di connessione.

```java
public synchronized static Connection getConnection() throws ClassNotFoundException, DAOException, IOException {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("properties/config.properties");
			Properties p = new Properties();
			p.load(input);

			Class.forName(p.getProperty("jdbcDriver"));
			conn = DriverManager.getConnection(p.getProperty("jdbcURL"), p.getProperty("jdbcUsername"),
					p.getProperty("jdbcPass"));
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

```

Implementazione di “idGenerators” per quasi ogni Entity

```java
package com.gab.business.idgenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gab.architecture.dao.DAOConstants;
import com.gab.architecture.dao.DAOException;
import com.gab.architecture.dbaccess.DBAccess;

public class ArticoloIdGenerator implements IdGeneratorInterface, DAOConstants{
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private static ArticoloIdGenerator idGen;
	
	private ArticoloIdGenerator() {
		
	}

	public static ArticoloIdGenerator getInstance() {
		if(idGen == null) {
			idGen = new ArticoloIdGenerator();
		}
		return idGen;
	}

	@Override
	public long getNextId() throws DAOException, ClassNotFoundException, IOException {
		long id = 0;
		try {
			conn = DBAccess.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ARTICOLO_SEQ);
			rs.next();
			id = rs.getLong(1);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return id;
	}
}

```

```java
public void createOrUpdate(Articolo articolo) throws DAOException, ClassNotFoundException, IOException {
		try {
			if (articolo.getIdArticolo() > 0) {
				aDAO.update(conn, articolo);
			} else {
				articolo.setIdArticolo(ArticoloIdGenerator.getInstance().getNextId());
				aDAO.create(conn, articolo);
			}
		} finally {
			DBAccess.closeConnection();
		}
	}
```

Viene usato all’interno della classe BC per capire se si tratta di un create or update
Caso create:

- L’id di quell’entity è = 0, vale a dire a quell’entity non gli è mai stato assegnato un id, quindi viene creata un record nel db, usando l’id successivo appena disponibile

Caso update:

- L’id di quell’entity già esiste e ha già un id > 0, per cui viene effettuato un update con riferimento all’id già esistente nell’entity Articolo.

- Il carrello del negozio viene inserito come bean all’interno delle pagine attraverso quest’action:

```java
<jsp:useBean id="carrello"
	class="com.gab.businesscomponenet.utility.Carrello" scope="session" />
```

- Implementazione dei BC per ogni DAO, quindi è stato implementato anche il DAO Pattern
- Implementazione delle facciate per racchiudere e organizzare tutti i BC creati
- Ogni evento all’interno dell’applicazione è stato gestito con le *WebServlet* di tipo HttpServlet. Quindi elebarovano dei dati, riempendo eventualmente i model e chiamavano il metodo della facciata necessario che a sua volta chiamava il componente BC, che a sua volta chiama il componente DAO.
- Infine effettuavano attraverso la response il redirect della pagina ad un’ altra.

- E’ presente anche un parte di amministrazione, dove sono insertite tutte le reportistiche principali che tengono traccia delle azioni più frequenti degli utenti. Essenzialmente basate su query sql e join fra tabelle
