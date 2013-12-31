package JenaUtils;

import java.util.List;

import googleplaces.City;
import googleplaces.CityInfo;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Resource;

public class DbPediaConnect {

	public String setSameAs(City city) {
		return "http://dbpedia.org/page/"+ city.getCityName();
	}
	
}
