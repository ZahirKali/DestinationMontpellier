package MainLauncher;

import interconnexion.DbPediaConnexion;
import googleplaces.City;
import googleplaces.GooglePlaceType;
import JenaUtils.ModelFactoryPlaces;
import JenaUtils.SDBUtils;
import apiutils.GooglePlaceCaller;



public class Main {
	public static void main(String[] args) {
		GetFromWebByType("alger", "food");
	}
	
	
	public static void CreateJenaModel() {
		//SDBUtils.emptySDBModel();
		ModelFactoryPlaces model = ModelFactoryPlaces.getMPlaces();
		model.toConsole();
	}

	public static City GetFromWeb(String city) {
		GooglePlaceCaller x = new GooglePlaceCaller(10000);
		City result =  x.villeEntitiesFromWeb(city);
		
		//System.out.println(result);
		result.toIndividual();
		return result;
	}
	
	public static void GetFromWebByType(String city, String type) {
		GooglePlaceCaller x = new GooglePlaceCaller(10000);
		City r =  x.villeEntitiesFromWebByTypes(city, type);
		r.toString();
	}
	
	
	}
