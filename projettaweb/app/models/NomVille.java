package models;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

public class NomVille extends Model {
	
	
	public String nom;
	
	
	 public NomVille() {}
	 
	 public NomVille(String nom)
	 {
		 this.nom=nom;
		 
	 }
	 
   public String getVille()
   {
	   return this.nom;
	   
   }
   public void setVille(String nom)
   {
	 this.nom=nom;
	   
   }
}
