package controllers;



import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

import models.Endpoint;
import models.Entity;
import models.GooglePlaceType;
import models.LesTypes;
import models.NomVille;
import models.OntClassType;
import models.Ville;
import models.City;
import models.GooglePlaceCaller;
import play.*;
import play.mvc.*;
import views.html.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	static String nom;
	 static List<String>  t;
    public static Result index() {
    	
    	
    	GooglePlaceCaller x = new GooglePlaceCaller();

		
		//final Form<NomVille> bookmarkForm = Form.form(NomVille.class).bindFromRequest();
        //final NomVille nom = bookmarkForm.get();

		 //Ville v = x.villeEntitiesFromWeb(nom.getVille());
    	  
    
    	
    	
        return ok(index.render("Bienvenu  ......"));
    }
    
    
    
    public static Result recherche() {
    	GooglePlaceCaller x = new GooglePlaceCaller(10000);
    	 t=new ArrayList<String>();
    	 nom=Form.form().bindFromRequest().get("nom");
    	
    	 GooglePlaceType[] GPT = GooglePlaceType.values();
 		for(GooglePlaceType gpt : GPT){
 		//System.out.println(gpt.name());
 			t.add(gpt.name());
 		}
    	 
    	 
    	 City v = x.villeEntitiesFromWeb(nom);
    	 
   	  v.toIndividual();
		  	
	        return ok(recherche.render(v,nom,t));
		 
		 }
  
    public static Result recherchebytype() {
    	GooglePlaceCaller x = new GooglePlaceCaller(10000);
    	
    	String types=Form.form().bindFromRequest().get("type");
    	
   	// LesTypes types = new LesTypes();
		 //City v = x.villeEntitiesFromWeb(nom);
    	City ville = x.villeEntitiesFromWebByTypes(nom, types);
    	ville.toIndividual();
    	
	        return ok(recherchebytyp.render("Bienvenu ...",ville,t));
		 
		 }
    
    
    
    
    public static Result requeteSparql() {
   
	        return ok(requetesparql.render("Bienvenu sur l'interface de requetage ..."));
		 
		 }
    
    
    public static Result resultatrequete() {
    	String lis=null;
    	Endpoint endpoint=new Endpoint();
    	String format=Form.form().bindFromRequest().get("format");
    	String query=Form.form().bindFromRequest().get("query");
    	ResultSet list= endpoint.sparqlQuery(query);
    	if(format.equals("text/html"))
    	{
    		 lis=ResultSetFormatter.asText(list);
    	}
    	if(format.equals("XML"))
    	{
    		lis=ResultSetFormatter.asXMLString(list);
    	}
    	
    	
		
    	
    //	List<String> lis=result.getResultVars();
    	
	        return ok(resultatreq.render("Bienvenu ...",lis));
		 
		 }
    
    
    
//    public static Result  detail(String lat,String lng) {
//    	
////    	GooglePlaceCaller x = new GooglePlaceCaller(10000);
////    	
//    	String  ent=Form.form().bindFromRequest().get("lat");
//    	String ent1=Form.form().bindFromRequest().get("lng");
//   	// LesTypes types = new LesTypes();
//		 //City v = x.villeEntitiesFromWeb(nom);
//    	//City ville = x.villeEntitiesFromWebByTypes(nom, types);
//	        return ok(detail.render("Bienvenu ...",ent));
//		 
//		 }
//   
    
}

