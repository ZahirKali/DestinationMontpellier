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

	public static String dpPediaNamespace = "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+"\n"
			+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+"\n"
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+"\n"
			+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+"\n"
			+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"+"\n"
			+ "PREFIX dc: <http://purl.org/dc/elements/1.1/>"+"\n"
			+ "PREFIX : <http://dbpedia.org/resource/>"+"\n"
			+ "PREFIX dbpedia2: <http://dbpedia.org/property/>"+"\n"
			+ "PREFIX dbpedia: <http://dbpedia.org/>"+"\n"
			+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>";

	public ResultSet executeQuerySparql(String query) {

		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", query);

		return qexec.execSelect();
	}

	public Resource setSameAs(City city) {
		ResultSet rest = executeQuerySparql(CreateQuerySparqlForDbpedia(null));
		try {
			while (rest.hasNext()) {
				QuerySolution sol = rest.next();
				System.err.println( sol.getResource("s"));
				return sol.getResource("s");
			}
		} catch (Exception e) {
			System.err.println("Impossible to find Resource for " + city + "!");
			return null;
		}
			return null;
	}

	public String CreateQuerySparqlForDbpedia(CityInfo city) {
		// city.getResults().get(0).getAddress_components().get(0).getShort_name()

		String q = " SELECT ?s WHERE {" + "?s dbpedia2:centre ?p ."+"\n"
				+ "FILTER regex(?p, \"Montpellier\", \"i\")."+"\n"
				+ "?s dbpedia2:latitude ?lat ."+"\n"
				+ "?s dbpedia2:longitude ?lon ."+"\n"
				+ "FILTER(?lat - 43.610769 <= 1 && 43.610769 - ?lat <= 1 &&"+"\n"
				+ "?lon - 3.876716 <= 1 && 3.876716 - ?lon <= 1)"+"\n"
				+ "} limit 10";

		q = dpPediaNamespace + q;
		System.out.println(q);
		return q;
	}
}
// http://dbpedia.org/resource/Montpellier
