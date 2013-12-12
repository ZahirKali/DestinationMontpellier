package MainLauncher;

import java.net.URLEncoder;

import googleplaces.City;
import JenaUtils.ModelFactoryPlaces;
import JenaUtils.SDBUtils;
import apiutils.GooglePlaceCaller;



public class Main {
	
	public static void main(String[] args) {
		CreateJENa();
		GetFromWeb();
	}

	public static void CreateJENa() {
		ModelFactoryPlaces model = ModelFactoryPlaces.getMPlaces();
		model.toConsole();
	}

	public static void GetFromWeb() {
		GooglePlaceCaller x = new GooglePlaceCaller(10000);
		City r =  x.villeEntitiesFromWeb("Juvignac");
	}

}
