package MainLauncher;

import googleplaces.*;
import googleplaces.City.component;
import JenaUtils.ModelFactoryPlaces;
import JenaUtils.SDBUtils;
import apiutils.GooglePlaceCaller;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.sdb.store.DatasetStore;


public class Main {
		
	public static void main(String[] args) {
		/*
		ModelFactoryPlaces model=new ModelFactoryPlaces();
		SDBUtils DB = new SDBUtils();
		DB.GetModelSDB();*/
		
		GooglePlaceCaller x = new GooglePlaceCaller();
		for (component comp: x.CityDetail("Lyon").getResults()){
			System.out.println("ADRESSE " +comp.getFormatted_address());
		}
		
	}
}



























