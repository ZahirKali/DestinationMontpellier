package MainLauncher;

import googleplaces.ResultSearchInCity;
import JenaUtils.ModelFactoryPlaces;
import apiutils.GooglePlaceCaller;

public class Main {

	public static void main(String[] args) {
		//GetFromWeb();
		CreateJENa();
	}

	public static void CreateJENa() {
		//SDBUtils.emptySDBModel();

		ModelFactoryPlaces model = ModelFactoryPlaces.getMPlaces();
		//model.toConsole();
	}

	public static void GetFromWeb() {
		
		GooglePlaceCaller x = new GooglePlaceCaller(10000);
		ResultSearchInCity r =  x.villeEntitiesFromWeb("Montpellier");
		
		
		System.out.println(r);
		
		
	}

}
