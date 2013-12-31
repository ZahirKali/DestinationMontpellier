package MainLauncher;

import googleplaces.City;
import googleplaces.Entity;
import JenaUtils.DbPediaConnect;
import JenaUtils.Endpoint;
import JenaUtils.ModelFactoryPlaces;
import apiutils.GooglePlaceCaller;



public class Main {
	static DbPediaConnect dbpc = null;
	
	public static void main(String[] args) {
		//String pr="PREFIX P : <http://localhost:9000/techweb/entity#>";
		Endpoint ep = new Endpoint();
		String query ="PREFIX p: <http://localhost:9000/techweb/city#>"
				+ " select ?s  ?o where {?s p:name ?o }";
		
		String q2 = "PREFIX p: <http://localhost:9000/techweb/city#>"
				+"PREFIX owl: <http://www.w3.org/2002/07/owl#>"
				+"PREFIX dbp: <http://dbpedia.org/ontology/>"
				+"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+"select   ?o where {p:name rdfs:comment ?o }";
		
		ep.sparqlQuery(q2);

		
	}
	
	public static void DbPedia() {
		dbpc = new  DbPediaConnect();
	}

	public static void CreateJenaModel() {
		ModelFactoryPlaces model = ModelFactoryPlaces.getMPlaces();
		model.toConsole();
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
