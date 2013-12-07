package googleplaces;

import java.util.List;

import JenaUtils.DumpString;
import JenaUtils.ModelFactoryPlaces;

import com.hp.hpl.jena.ontology.Individual;


public class Entity{
   	protected Geometry geometry;
   	protected String icon;
   	protected String id;
   	protected String name;
   	protected String reference;
   	protected List<String> types;
   	protected String vicinity;

 	public Geometry getGeometry(){
		return this.geometry;
		
	}
	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}
 	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getReference(){
		return this.reference;
	}
	public void setReference(String reference){
		this.reference = reference;
	}
 	public List<String> getTypes(){
		return this.types;
	}
	public void setTypes(List<String> types){
		this.types = types;
	}
 	public String getVicinity(){
		return this.vicinity;
	}
	public void setVicinity(String vicinity){
		this.vicinity = vicinity;
	}
	
	@Override
	public String toString() {
		return DumpString.dumpString(this);
	}
	
	public Individual toIndividual(){
		
		ModelFactoryPlaces model = ModelFactoryPlaces.getMPlaces();
		
		Individual m = model.getEntity().createIndividual(id);
		
		
		
		
		
		return m;
	}
	
	public static void fromIndividual(Individual indevedu){
		
	}
}
