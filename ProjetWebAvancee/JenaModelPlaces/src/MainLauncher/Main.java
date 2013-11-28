package MainLauncher;

import googleplaces.*;
import JenaUtils.ModelFactoryPlaces;
import JenaUtils.SDBUtils;
import apiutils.GooglePlaceCaller;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.sdb.store.DatasetStore;


public class Main {
	
	
	public static void main(String[] args) {
		
		ModelFactoryPlaces model=new ModelFactoryPlaces();
		SDBUtils DB = new SDBUtils();
		DB.GetModelSDB();
		
		
		GooglePlaceCaller x = new GooglePlaceCaller();
		
		Ville v = x.villeLodgingsFromWeb("Montpellier");
		
		for (Entity ent : v.getResults()) {
			System.out.print(ent.getName()+"\n");
		}
	}
}



























