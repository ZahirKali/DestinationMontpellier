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
	private static final String user = "root";
	private static final String psw = "021288";
	
	public static OntModel GetModelSDB() {
			/****************************************************
		    * CONNEXION DE SDB A MySQL
		    ****************************************************/
		   StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash, DatabaseType.MySQL) ;
		   JDBC.loadDriverMySQL();
		   String jdbcURL = "jdbc:mysql://localhost:3306/technoweb";
		   SDBConnection conn = new SDBConnection(jdbcURL, user, psw);
		   
		   Store store = SDBFactory.connectStore(conn, storeDesc) ;
		   
		   if(conn != null){
			   System.out.println("CONNECTED TO MySQL DATABASE ..");
		   }else{
			   System.out.println("CONNECTION FAILED !!");
		   }
		   
		   /****************************************************
		    * CREER LA BASE DE DONNEES
		    ****************************************************/
		   store.getTableFormatter().create();

		   Model model = SDBFactory.connectDefaultModel(store);
		   OntModel mdb = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, model);
		   return mdb;
	}

}
