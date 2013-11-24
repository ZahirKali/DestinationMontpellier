package MainLauncher;

import googleplaces.Entity;
import googleplaces.Ville;
import JenaUtils.ModelFactoryPlaces;
import JenaUtils.SDBUtils;
import apiutils.GooglePlaceCaller;




public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		ModelFactoryPlaces model=new ModelFactoryPlaces();
//		SDBUtils DB = new SDBUtils();
//		DB.GetModelSDB();
		
		
		GooglePlaceCaller x = new GooglePlaceCaller();
		
		Ville v = x.villeEntitiesFromWeb("bougie");
		
		
		for (Entity ent : v.getResults()) {
			System.out.print(ent.getName() +"*");
			for (String str : ent.getTypes()) {
				System.out.print(str +" / ");
			}
			System.out.println();
			System.out.println("-------------------------------------------------------------------");
		}
	}
}
