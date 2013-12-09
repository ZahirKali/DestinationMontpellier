package MainLauncher;

import googleplaces.City;
import JenaUtils.ModelFactoryPlaces;
import JenaUtils.SDBUtils;
import apiutils.GooglePlaceCaller;



public class Main {
	
	public static void main(String[] args) {
		
		
		//CreateJENa();
		GetFromWeb();
		//ModelFactoryPlaces model=ModelFactoryPlaces.getInstance();
		
		
		// GooglePlaceCaller place = new GooglePlaceCaller();
		// City lyon = place.villeEntitiesFromWeb("Lyon");
		// SDBUtils.insertEntitiesIntoModel(lyon);
		// String prefix="http://localhost:9000/techweb#";
		// Query query = QueryFactory.create("select ?class where{ ?class subClassOf ?Entity}") ;
		
		//DB.executeQuery(query);

	}

	public static void CreateJENa() {
		//SDBUtils.emptySDBModel();

		ModelFactoryPlaces model = ModelFactoryPlaces.getMPlaces();
		model.toConsole();
	}

	public static void GetFromWeb() {
		
		GooglePlaceCaller x = new GooglePlaceCaller(10000);
		//City r =  x.villeEntitiesFromWeb("Montpellier");
		
		
		System.out.println(x.locationFromcityName("Montpellier"));
		
		
	}

}
