package JenaUtils;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.StoreDesc;
import com.hp.hpl.jena.sdb.sql.JDBC;
import com.hp.hpl.jena.sdb.sql.SDBConnection;
import com.hp.hpl.jena.sdb.store.DatabaseType;
import com.hp.hpl.jena.sdb.store.LayoutType;

public class SDBUtils {

	public static OntModel GetModelSDB() {

		System.setProperty("jena.db.user", "root");
		System.setProperty("jena.db.password", "021288");
		
		StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash, DatabaseType.MySQL);
		
		JDBC.loadDriverMySQL();
		String jdbcURL = "jdbc:mysql://localhost:3306/technoweb";
		
		SDBConnection conn = new SDBConnection(jdbcURL, "root", "021288");
		
		if(conn !=null){
			System.out.println("CONNEXION REUSSIIIIII");
		}
		
		Store store = SDBFactory.connectStore(conn, storeDesc);
		/*
		if (store.getSize() > 0) {
			
			store.getTableFormatter().create();
		}
		System.out.println("CONNEXION REUSSIIIIII");
		Model model = SDBFactory.connectDefaultModel(store);

		OntModel mdb = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, model);
*/
		return null;
	}

}
