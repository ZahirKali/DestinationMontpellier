package JenaUtils;

import googleplaces.City;
import googleplaces.Entity;
import googleplaces.Location;
import googleplaces.types.Airport;
import googleplaces.types.Food;
import googleplaces.types.Lodging;
import googleplaces.types.Museum;
import MainLauncher.Main;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.StoreDesc;
import com.hp.hpl.jena.sdb.sql.JDBC;
import com.hp.hpl.jena.sdb.sql.SDBConnection;
import com.hp.hpl.jena.sdb.store.DatabaseType;
import com.hp.hpl.jena.sdb.store.DatasetStore;
import com.hp.hpl.jena.sdb.store.LayoutType;


public class SDBUtils {
	private static final String user = "root";
	private static final String psw = "";
	private static Store store;
	//obtenir une ontologie persistante
	public static OntModel GetModelSDB() { 
			/****************************************************
		    * CONNEXION DE SDB A MySQL
		    ****************************************************/
		   StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash, DatabaseType.MySQL) ;
		   JDBC.loadDriverMySQL();
		   String jdbcURL = "jdbc:mysql://localhost:3306/technoweb";
		   SDBConnection conn = new SDBConnection(jdbcURL, user, psw);
		   
		    store = SDBFactory.connectStore(conn, storeDesc) ;
		   
		   if(conn != null){
			   System.out.println("CONNECTED TO MySQL DATABASE ..");
		   }else{
			   System.out.println("CONNECTION FAILED !!");
		   }
		   
		   /****************************************************
		    * CREER LA BASE DE DONNEES
		    ****************************************************/
		   store.getTableFormatter().create();
		   
		   Model model = SDBFactory.connectDefaultModel(store); //mettre model en BDD
		   OntModel mdb = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, model);
		   
		   return mdb;
	}
	
	public void executeQuery(Query query){
		Dataset ds = DatasetStore.create(store) ;
		   QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
		   try {
		       ResultSet rs = qe.execSelect() ;
		       ResultSetFormatter.out(rs) ;
		   } finally { qe.close() ; }

	}
	
	/****************************************************
	    * INSERTION DES DONNEES DANS LE MODEL
	****************************************************/
	public static void insertEntitiesIntoModel(City city){
		insertCityIntoModel(city);		
		for(Entity ent: city.getResults()){
			if(ent.getTypes().contains("food")){
				ent = new Food();
				Main.DB.insertFoodIntoModel((Food)ent);
			}
			if(ent.getTypes().contains("airport")){
				ent = new Airport();
				Main.DB.insertAirportIntoModel((Airport)ent);
			}
			if(ent.getTypes().contains("lodging")){
				ent = new Lodging();
				Main.DB.insertLodgingIntoModel((Lodging)ent);
			}
			if(ent.getTypes().contains("museum")){
				ent = new Museum();
				Main.DB.insertMuseumIntoModel((Museum)ent);
			}
		}
	}
	
	private static void insertCityIntoModel(City city){
		Individual ind =  Main.model.CreateCityInstance(city);
		Main.MyOntModel.createIndividual(ind);
	}
	
	private static void insertEntityIntoModel(Entity ent){
		Individual ind =  Main.model.CreateEntityInstance(ent);
		Main.MyOntModel.createIndividual(ind);
	}
	private static void insertLocationIntoModel(Location loc){
		Individual ind =  Main.model.CreateLocationInstance(loc);
		Main.MyOntModel.createIndividual(ind);
	}
	private static void insertAirportIntoModel(Airport air){
		Individual ind =  Main.model.CreateAireportInstance(air);
		Main.MyOntModel.createIndividual(ind);
	}
	private static void insertFoodIntoModel(Food food){
		Individual ind =  Main.model.CreateFoodInstance(food);
		Main.MyOntModel.createIndividual(ind);
	}
	private static void insertLodgingIntoModel(Lodging lod){
		Individual ind =  Main.model.CreateLodgingInstance(lod);
		Main.MyOntModel.createIndividual(ind);
	}
	private static void insertMuseumIntoModel(Museum mus){
		Individual ind =  Main.model.createMuseumInstance(mus);
		Main.MyOntModel.createIndividual(ind);
	}
	
}
