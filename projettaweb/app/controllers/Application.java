package controllers;

import models.NomVille;
import models.Ville;
import models.GooglePlaceCaller;
import play.*;
import play.mvc.*;
import views.html.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {
    	
    	
    	GooglePlaceCaller x = new GooglePlaceCaller();

		
		//final Form<NomVille> bookmarkForm = Form.form(NomVille.class).bindFromRequest();
        //final NomVille nom = bookmarkForm.get();

		 //Ville v = x.villeEntitiesFromWeb(nom.getVille());
    	  
    
    	
    	
        return ok(index.render("Bienvenu  ......"));
    }
    
    
    
    public static Result recherche() {
    	GooglePlaceCaller x = new GooglePlaceCaller();
    	
    	String nom=Form.form().bindFromRequest().get("nom");
    	
		 Ville v = x.villeEntitiesFromWeb(nom);
    	  
		  	
	        return ok(recherche.render("Bienvenu ...",v,nom));
		 
		 }
  
}

