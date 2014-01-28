package models;



import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Resource;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class Endpoint{
	private static final String prefix =
			"PREFIX geoloc: <http://localhost:9000/techweb/location/>"
			+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns/>"
			+ "PREFIX entity: <http://localhost:9000/techweb/entity#/>"
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#/>"
			+ "PREFIX city: <http://localhost:9000/techweb/city#/>"
			+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/>"
			+ "PREFIX places: <http://localhost:9000/techweb#/>"
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/>"
			;
	
	public ResultSet sparqlQuery(String query){
		 QueryExecution x = QueryExecutionFactory.create(prefix + query, SDBUtils.getModelSDB());
		
		 ResultSet results = x.execSelect();
	//	String re=ResultSetFormatter.asXMLString(results);
	//	ResultSetFormatter.asText(results).format(format, args);
		
	//Resource r=ResultSetFormatter.asRDF(SDBUtils.getModelSDB(),true);
	//String re=r.getLocalName();
		 return results;
		// ResultSetFormatter.out(System.out, results);
	
//		 try{
//			 ResultSet results = x.execSelect();
//			
//				while(results.hasNext()){
//					QuerySolution sol=results.nextSolution();
//					
////					sol.getResource();
////					Resource s = results.nextResource();
////				  	s.getLocalName();
//				//	skos:prefLabel
//					//Literal node=sol.getLiteral("y");
//					
//					
//					//System.out.println(node.toString());
//					// RDFNode node = sol.get("x");
//					 // System.out.println(node.g);
//					//  node.visitWith(aVisitor);
//					//  System.out.println(sol);
//				//	Resource  o=sol.getResource("x");
//					//System.out.println(o.getLocalName());
//			//	System.out.println(o);
//				}
//			}
//			finally{
//				x.close();}
//			
//		}
		 
		 
	}
	
}