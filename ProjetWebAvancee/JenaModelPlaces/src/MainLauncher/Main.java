package MainLauncher;

import sdb.sdbquery;
import googleplaces.*;
import googleplaces.CityInfo.component;
import googleplaces.types.Airport;
import googleplaces.types.Food;
import googleplaces.types.Lodging;
import googleplaces.types.Museum;
import JenaUtils.ModelFactoryPlaces;
import JenaUtils.SDBUtils;
import apiutils.GooglePlaceCaller;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.store.DatasetStore;


public class Main {
	public static OntModel MyOntModel;
	public static ModelFactoryPlaces model;
	public static SDBUtils DB;
	
	public static void main(String[] args) {
		model=new ModelFactoryPlaces();
		DB = new SDBUtils();
		MyOntModel = DB.GetModelSDB();

		GooglePlaceCaller place = new GooglePlaceCaller();
		City lyon = place.villeEntitiesFromWeb("Lyon");
		DB.insertEntitiesIntoModel(lyon);
		String prefix="http://localhost:9000/techweb#";
		//Query query = QueryFactory.create("select ?class where{ ?class subClassOf ?Entity}") ;
		
		//DB.executeQuery(query);
	}
}



























