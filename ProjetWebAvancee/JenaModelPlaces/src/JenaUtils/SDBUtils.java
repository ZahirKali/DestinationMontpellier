package JenaUtils;

import googleplaces.City;
import googleplaces.Entity;
import googleplaces.Location;
import googleplaces.types.Airport;
import googleplaces.types.Food;
import googleplaces.types.Lodging;
import googleplaces.types.Museum;

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
	private static final String psw = "";// "021288";
	private static Store store = null;

	public static OntModel getModelSDB() {
		/****************************************************
		 * CONNEXION DE SDB A MySQL
		 ****************************************************/
		StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash,
				DatabaseType.MySQL);
		JDBC.loadDriverMySQL();
		String jdbcURL = "jdbc:mysql://localhost:3306/places_rdf";
		SDBConnection conn = null;
		try {
			conn = new SDBConnection(jdbcURL, user, psw);
			System.out.println("MODEL GETTING ..");
		} catch (Exception e) {
			System.out.println("CONNECTION FAILED !!");
			e.printStackTrace();
		}

		store = SDBFactory.connectStore(conn, storeDesc);

		Model model = SDBFactory.connectDefaultModel(store);
		OntModel mdb = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
				model);

		return mdb;
	}

	public void executeQuery(Query query) {
		Dataset ds = DatasetStore.create(store);
		QueryExecution qe = QueryExecutionFactory.create(query, ds);
		try {
			ResultSet rs = qe.execSelect();
			ResultSetFormatter.out(rs);
		} finally {
			qe.close();
		}
	}

	/****************************************************
	 * INSERTION DES DONNEES DANS LE MODEL
	 ****************************************************/
	public static void insertEntitiesIntoModel(City city) {
		insertCityIntoModel(city);  
		for (Entity ent : city.getResults()) {
			if (ent.getTypes().contains("food")) {
				ent  = new Food();
				insertFoodIntoModel((Food) ent);
			}
			if (ent.getTypes().contains("airport")) {
				ent = new Airport();
				insertAirportIntoModel((Airport) ent);
			}
			if (ent.getTypes().contains("lodging")) {
				ent = new Lodging();
				insertLodgingIntoModel((Lodging) ent);
			}
			if (ent.getTypes().contains("museum")) {
				ent = new Museum();
				insertMuseumIntoModel((Museum) ent);
			}
		}
	}

	private static void insertCityIntoModel(City city) {
		Individual ind = ModelFactoryPlaces.getInstance().CreateCityInstance(
				city);
		ModelFactoryPlaces.getInstance().getModel().createIndividual(ind);
	}

	private static void insertEntityIntoModel(Entity ent) {
		Individual ind = ModelFactoryPlaces.getInstance().CreateEntityInstance(
				ent);
		ModelFactoryPlaces.getInstance().getModel().createIndividual(ind);
	}

	private static void insertLocationIntoModel(Location loc) {
		Individual ind = ModelFactoryPlaces.getInstance()
				.CreateLocationInstance(loc);
		ModelFactoryPlaces.getInstance().getModel().createIndividual(ind);
	}

	private static void insertAirportIntoModel(Airport air) {
		Individual ind = ModelFactoryPlaces.getInstance()
				.CreateAireportInstance(air);
		ModelFactoryPlaces.getInstance().getModel().createIndividual(ind);
	}

	private static void insertFoodIntoModel(Food food) {
		Individual ind = ModelFactoryPlaces.getInstance().CreateFoodInstance(
				food);
		ModelFactoryPlaces.getInstance().getModel().createIndividual(ind);
	}

	private static void insertLodgingIntoModel(Lodging lod) {
		Individual ind = ModelFactoryPlaces.getInstance()
				.CreateLodgingInstance(lod);
		ModelFactoryPlaces.getInstance().getModel().createIndividual(ind);
	}

	private static void insertMuseumIntoModel(Museum mus) {
		Individual ind = ModelFactoryPlaces.getInstance().createMuseumInstance(
				mus);
		ModelFactoryPlaces.getInstance().getModel().createIndividual(ind);
	}

	public static void emptySDBModel() {
		StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash,
				DatabaseType.MySQL);
		JDBC.loadDriverMySQL();
		String jdbcURL = "jdbc:mysql://localhost:3306/places_rdf";
		SDBConnection conn = null;
		try {
			conn = new SDBConnection(jdbcURL, user, psw);
			System.out.println("DROPPING ALL ELEMENTS ...");
		} catch (Exception e) {
			System.out.println("CONNECTION FAILED !!");
			e.printStackTrace();
		}

		Store store = SDBFactory.connectStore(conn, storeDesc);

		store.getTableFormatter().truncate();

		conn.close();

	}

	/**
	 * Creation des tables
	 */
	public static void createSDBModel() {
		StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash,
				DatabaseType.MySQL);
		JDBC.loadDriverMySQL();
		String jdbcURL = "jdbc:mysql://localhost:3306/places_rdf";
		SDBConnection conn = null;
		try {
			conn = new SDBConnection(jdbcURL, user, psw);
			System.out.println("CREATE TABLES  ..");
		} catch (Exception e) {
			System.out.println("CONNECTION FAILED !!");
			e.printStackTrace();
		}

		Store store = SDBFactory.connectStore(conn, storeDesc);
		store.getTableFormatter().create();

		conn.close();
	}

}
