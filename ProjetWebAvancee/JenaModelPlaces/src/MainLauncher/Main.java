package MainLauncher;

import googleplaces.City;
import googleplaces.Entity;
import JenaUtils.DbPediaConnect;
import JenaUtils.Endpoint;
import JenaUtils.ModelFactoryPlaces;
import apiutils.GooglePlaceCaller;



public class Main {
	
	public static void main(String[] args) {
		Endpoint ep = new Endpoint();
		String query ="select * where {"
				+ "?s entity:id ?o }";
		
		ep.sparqlQuery(query);
		//CreateJenaModel();
		DbPedia();
	}
	
	public static void DbPedia() {
		DbPediaConnect x = new DbPediaConnect();
		x.setSameAs(null);
	}

	public static void CreateJenaModel() {
		ModelFactoryPlaces model = ModelFactoryPlaces.getMPlaces();
		model.toConsole();
	}

	public static void GetFromWeb(String city) {
		GooglePlaceCaller x = new GooglePlaceCaller(10000);
		City r =  x.villeEntitiesFromWeb(city);
		r.toIndividual();
		ModelFactoryPlaces.getMPlaces().toConsole();
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
