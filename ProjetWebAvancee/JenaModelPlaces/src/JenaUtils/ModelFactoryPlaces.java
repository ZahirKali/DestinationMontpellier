package JenaUtils;
import googleplaces.*;

import org.omg.CORBA.Object;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.XSD;


public class ModelFactoryPlaces {
	private OntModel model;
	private String namespace = "http://localhost:9000/techweb";
	private String ns_ville = "http://localhost:9000/techweb#ville";
	private String ns_entity = "http://localhost:9000/techweb#entity";
	private String ns_location = "http://localhost:9000/techweb#location";
	private OntClass ville;
	private OntClass entity;
	private OntClass location;
	
	public ModelFactoryPlaces(){
		CreateOntologie();
	}
	
	/***************************************************************************************************************************
	 * CREATION DU MODEL
	 * *************************************************************************************************************************/
	public void CreateOntologie(){
		model = ModelFactory.createOntologyModel();
		model.createOntology (namespace);
		CreateOntClasses();
	}
	
	/******************************************************************************************************************************
	 * CREATION DES CLASSES
	 * ****************************************************************************************************************************/
	public void CreateOntClasses(){
		ville = model.createClass (namespace + "#ville");
		entity = model.createClass (namespace + "#entity");
		location = model.createClass (namespace + "#location");
		AddVilleProperties();
		AddEntityProperty();
		AddLocationProperty();
		AddVilleLocationProperty();
		AddEntityLocationProperty();
		toConsole();
	}
	
	/******************************************************************************************************************************
	 * CREATION ET AJOUT DE PROPRIETEES
	 *****************************************************************************************************************************/
	public OntProperty CreateProperty( OntClass classe, String namespace, String propertyName, String comment, String label, Resource resource) {
		OntProperty property = model.createOntProperty( namespace + propertyName);
		property.setDomain(classe);
		property.setRange(resource);
		property.addComment(comment, "fr");
		property.setLabel(label, "en");

		return property;
	}
	public ObjectProperty CreateObjectProperty(String propertyName, OntClass domaine, OntClass range, String comment, String label){
		ObjectProperty ObjProperty = model.createObjectProperty(namespace+propertyName);
		ObjProperty.setDomain(domaine);
		ObjProperty.setRange(range);
		ObjProperty.setComment(comment, "fr");
		ObjProperty.setLabel(label, "en");
		return ObjProperty;
	}
	public void AddVilleProperties(){
		ville.addProperty(CreateProperty(ville, ns_ville, "nom", "Le nom de la ville", "city name", XSD.xstring), ns_ville);
		ville.addProperty(CreateProperty(ville, ns_ville, "formatted_addres", "l'address de la ville", "Ville address", XSD.xstring), ns_ville);
		ville.addProperty(CreateProperty(ville, ns_ville, "type", "le type de la ville", "Ville Types", XSD.xstring), ns_ville);
	}
	public void AddEntityProperty(){
		entity.addProperty(CreateProperty(entity, ns_entity, "name", "le nom de l'entity", "Entity Name", XSD.Name), ns_entity);
		entity.addProperty(CreateProperty(entity, ns_entity, "id", "l'identifiant de l'entity", "Entity Id", XSD.ID), ns_entity);
		entity.addProperty(CreateProperty(entity, ns_entity, "type", "le type de l'entity", "Entity Types", RDF.List), ns_entity);
		entity.addProperty(CreateProperty(entity, ns_entity, "adresse", "l'adresse de l'entité", "Entity Adress", XSD.xstring), ns_entity);
	}
	public void AddLocationProperty(){
		location.addProperty(CreateProperty(location, ns_location, "lat", "latitude de l'entity", "loc latitude", XSD.xdouble), ns_location);
		location.addProperty(CreateProperty(location, ns_location, "lng", "longitude de l'entity", "loc longitude", XSD.xdouble), ns_location);
	}
	
	public void AddVilleLocationProperty(){
		CreateObjectProperty("aPourLocation", ville, location,"Localisation d'une ville","Localisation of a city");
	}
	public void AddEntityLocationProperty(){
		CreateObjectProperty("aPourLocation", entity, location,"Localisation d'une entité","Localisation of an entity");
	}
	/******************************************************************************************************************************
	 * CREATION DES INSTANCES
	 *****************************************************************************************************************************/
	public void CreateVilleInstance(Ville v){
		
	}
	
	
	/******************************************************************************************************************************
	 * AFFICHAGE DE L'ONTOLOGIE
	 *****************************************************************************************************************************/
	public void toConsole(){
		model.write(System.out,"RDF/XML-ABBREV");
	}
	
	}
