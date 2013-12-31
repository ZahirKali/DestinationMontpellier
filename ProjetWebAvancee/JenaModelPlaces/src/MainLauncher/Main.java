package MainLauncher;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

import googleplaces.City;
import googleplaces.Entity;
import JenaUtils.DbPediaConnect;
import JenaUtils.Endpoint;
import JenaUtils.ModelFactoryPlaces;
import apiutils.GooglePlaceCaller;



public class Main {
	static DbPediaConnect dbpc = null;
	
	public static void main(String[] args) {
		Endpoint ep = new Endpoint();

		CreateJenaModel();
		ModelFactoryPlaces MPF = ModelFactoryPlaces.getMPlaces();
		MPF.exec();
	}
	
	public static void DbPedia() {
		dbpc = new  DbPediaConnect();
	}

	public static void CreateJenaModel() {
		ModelFactoryPlaces model = ModelFactoryPlaces.getMPlaces();
		//model.toConsole();
	}

	public static City GetFromWeb(String city) {
		GooglePlaceCaller x = new GooglePlaceCaller(10000);
		City result =  x.villeEntitiesFromWeb(city);
		result.toIndividual();
		return result;
	}
	
	public static void GetFromWebByType(String city, String type) {
		GooglePlaceCaller x = new GooglePlaceCaller(10000);
		City r =  x.villeEntitiesFromWebByTypes(city, type);
/*		r.toIndividual();
		for(Entity ent : r.getResults()){
			System.out.println(ent.getName());
		}
*///		ModelFactoryPlaces.getMPlaces().toConsole();
	}
}
