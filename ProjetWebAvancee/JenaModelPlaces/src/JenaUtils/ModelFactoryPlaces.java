package JenaUtils;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.XSD;

public class ModelFactoryPlaces {
	private OntModel model;
	private String namespace = "http://localhost:9000/techweb#";
	private String ns_city = "http://localhost:9000/techweb/city#";
	private String ns_entity = "http://localhost:9000/techweb/entity#";
	private String ns_location = "http://localhost:9000/techweb/location#";
	
	private OntClass city;
	private OntClass entity;
	private OntClass location;
	private OntClass transport;
	private OntClass food;
	private OntClass lodging;
	private OntClass health;
	private OntClass money;
	private OntClass religion;
	private OntClass loisirs;

	static ModelFactoryPlaces singleton = null;

	private ModelFactoryPlaces() {
		CreateIfNotExistOntologie();
	}

	public OntModel getModel() {
		return model;
	}

	public static ModelFactoryPlaces getMPlaces() {
		if (singleton == null) {
			singleton = new ModelFactoryPlaces();
		}
		return singleton;

	}

	public static ModelFactoryPlaces getInstance() {
		if (singleton == null) {
			singleton = new ModelFactoryPlaces();
		}
		return singleton;

	}

	/***************************************************************************************************************************
	 * CREATION DU MODEL
	 * *************************************************************************************************************************/
	public void CreateIfNotExistOntologie() {

		model = SDBUtils.getModelSDB();
		Iterator<OntClass> cl = model.listClasses();

		/*
		 * Si La base est deja creer
		 */
		if (cl.hasNext()) {
			System.out.println("Getting existing ");
			do {
				OntClass c = cl.next();

				OntClassType type = OntClassType.valueOf(c.getLocalName());
				switch (type) {
				case city:
					city = c;
					break;

				case airport:
					transport = c;
					break;

				case entity:
					entity = c;
					break;
				case food:
					food = c;
					break;
				case location:
					location = c;
					break;
				case lodging:
					location = c;
					break;
				case museum:
					loisirs = c;
					break;

				default:
					break;
				}

				System.err.println(c.getLocalName());
				Iterator<Statement> ps = c.listProperties();
				while (ps.hasNext()) {
					Statement p = ps.next();
					System.out.println(p);

				}
			} while (cl.hasNext());
		}

		/* sinon on la cree */
		else {
			System.out.println("Creating Ont Class ");
			CreateOntClasses();
		}

	}

	/**
	 * Create Ont Classes of Places Model
	 */
	public void CreateOntClasses() {

		model.setNsPrefix("places", namespace);

		city = model.createClass(namespace + "city");
		entity = model.createClass(namespace + "entity");
		location = model.createClass(namespace + "location");
		
		transport = model.createClass(namespace + "transport");
		food = model.createClass(namespace + "food");
		lodging = model.createClass(namespace + "lodging");
		loisirs = model.createClass(namespace + "loisirs");
		religion = model.createClass(namespace + "religion");
		religion = model.createClass(namespace + "health");
		religion = model.createClass(namespace + "loisirs");
		
		
		AddcityProperties();
		AddEntityProperty();
		AddLocationProperty();
		AddcityLocationProperty();
		AddEntityLocationProperty();
		AddSubClasses();

	}

	/**
	 * Create a Property
	 * 
	 * CreateProperty(entity, ns_entity, "name", "le nom de l'entity",
	 * "Entity Name", XSD.xstring), ns_entity);
	 * 
	 * @param classe
	 *            OntClass
	 * @param namespace
	 * @param propertyName
	 * @param comment
	 * @param label
	 * @param resource
	 * @return
	 */
	public OntProperty CreateProperty(OntClass classe, String namespace,
			String propertyName, String comment, String label, Resource resource) {
		OntProperty property = model
				.createOntProperty(namespace + propertyName);
		property.setDomain(classe);
		property.setRange(resource);
		property.addComment(comment, "fr");
		property.setLabel(label, "en");

		return property;
	}

	/**
	 * Create Object Property
	 * 
	 * @param propertyName
	 * @param domaine
	 * @param range
	 * @param comment
	 * @param label
	 * @return
	 */
	public ObjectProperty CreateObjectProperty(String propertyName,
			OntClass domaine, OntClass range, String comment, String label) {
		ObjectProperty ObjProperty = model.createObjectProperty(namespace
				+ propertyName);
		ObjProperty.setDomain(domaine);
		ObjProperty.setRange(range);
		ObjProperty.setComment(comment, "fr");
		ObjProperty.setLabel(label, "en");
		return ObjProperty;
	}

	void AddcityProperties() {
		city.addProperty(
				CreateProperty(city, ns_city, "name", "Le nom de la city",
						"city name", XSD.xstring), ns_city);
		city.addProperty(
				CreateProperty(city, ns_city, "formatted_address",
						"l'address de la city", "city address", XSD.xstring),
				ns_city);
		city.addProperty(
				CreateProperty(city, ns_city, "location",
						"la localisation de la city ", "city localisation",
						location), ns_city);
	}

	void AddEntityProperty() {

		entity.addProperty(
				CreateProperty(entity, ns_entity, "name", "le nom de l'entity",
						"Entity Name", XSD.xstring), ns_entity);
		entity.addProperty(
				CreateProperty(entity, ns_entity, "id",
						"l'identifiant de l'entity", "Entity Id", XSD.ID),
				ns_entity);
		entity.addProperty(
				CreateProperty(entity, ns_entity, "type",
						"le type de l'entity", "Entity Types", RDF.List),
				ns_entity);
		entity.addProperty(
				CreateProperty(entity, ns_entity, "address",
						"l'adresse de l'entité", "Entity Adress", XSD.xstring),
				ns_entity);
	}

	void AddLocationProperty() {
		location.addProperty(
				CreateProperty(location, ns_location, "lat",
						"latitude de l'entity", "loc latitude", XSD.xdouble),
				ns_location);
		location.addProperty(
				CreateProperty(location, ns_location, "lng",
						"longitude de l'entity", "loc longitude", XSD.xdouble),
				ns_location);
	}

	void AddcityLocationProperty() {
		CreateObjectProperty("aPourLocation", city, location,
				"Localisation d'une city", "Localisation of a city");
	}

	void AddEntityLocationProperty() {
		CreateObjectProperty("aPourLocation", entity, location,
				"Localisation d'une entité", "Localisation of an entity");
	}

	void AddSubClasses() {

		entity.addSubClass(transport);
		entity.addSubClass(food);
		entity.addSubClass(lodging);
		entity.addSubClass(loisirs);
		entity.addSubClass(religion);
		entity.addSubClass(health);
		entity.addSubClass(loisirs);
	}

//	/******************************************************************************************************************************
//	 * CREATION DES INSTANCES
//	 *****************************************************************************************************************************/
//	public Individual CreateCityInstance(City v) {
//		Individual vil = city.createIndividual(ns_city + v);
//		return vil;
//	}
//
//	public Individual CreateEntityInstance(Entity e) {
//		Individual ent = entity.createIndividual(ns_entity + e);
//		return ent;
//	}
//
//	public Individual CreateLocationInstance(Location l) {
//		Individual loc = location.createIndividual(ns_location + l);
//		return loc;
//	}
//
//	public Individual CreateAireportInstance(Airport a) {
//		Individual air = airport.createIndividual(ns_airport + a);
//		return air;
//	}
//
//	public Individual CreateFoodInstance(Food f) {
//		Individual foo = food.createIndividual(ns_food + f);
//		return foo;
//	}
//
//	public Individual CreateLodgingInstance(Lodging l) {
//		Individual lod = lodging.createIndividual(ns_lodging + l);
//		return lod;
//	}
//
//	public Individual createMuseumInstance(Museum m) {
//		Individual mus = museum.createIndividual(ns_museum + m);
//		return mus;
//	}

	/******************************************************************************************************************************
	 * AFFICHAGE DE L'ONTOLOGIE
	 *****************************************************************************************************************************/
	public void toConsole() {
		model.write(System.out, "RDF/XML-ABBREV");
	}

	/**
	 * Get Airport OntClass
	 * 
	 * @return @{OntClass}
	 */
	public OntClass getAirport() {
		return transport;
	}

	/**
	 * Get City OntClass
	 * 
	 * @return @{OntClass}
	 */
	public OntClass getCity() {
		return city;
	}

	/**
	 * ....
	 * 
	 * @return
	 */
	public OntClass getFood() {
		return food;
	}

	public OntClass getEntity() {
		return entity;
	}

	public OntClass getLocation() {
		return location;
	}

	public OntClass getLodging() {
		return lodging;
	}

	public OntClass getMuseum() {
		return loisirs;
	}

	public OntClass getClassByString(String ontclassName) {

		OntClass ret = null;
		try {
			OntClassType name = OntClassType.valueOf(ontclassName);
			switch (name) {
			case city:
				ret = city;
				break;

			
			case church:
			case mosque:
			case hindu_temple:
				ret =religion;
				break;

			case amusement_park:
			case aquarium:
			case art_gallery:
			case bowling_alley:
			case florist:
			case gym:
			case zoo:
			case stadium:
			case museum:
			case movie_theater:
				ret = loisirs;
				break;

			case university:
			case school:
			 
			 	break;
				
			case food:
			case bar:
			case restaurant:
			case cafe:
				ret = food;
				break;

			case location:
				ret = location;
				break;

			
			case travel_agency:
			case lodging:
				ret = lodging;
				break;

			case accounting:
			case bank:
			case bicycle_store:
			case book_store:
			case beauty_salon:
			case casino:
			case department_store:
			case store:
			case clothing_store:
				ret = money;
				break;

			case doctor:
			case health:
			case hospital:
			case dentist:
			case pharmacy:
			case veterinary_care:
				ret = health;
				break;

				
			case train_station:
			case bus_station:
			case parking:
			case subway_station:
			case airport:
			case taxi_stand:
				ret = transport;
				break;
				
			case entity:
				break;

			default:
				ret = null;
			}

		} catch (Exception e) {
			System.out.println(" ONT CLASS Dont EXIST : " + ontclassName);
		}

		return ret;

	}

	/**
	 * Get City NameSpace
	 * 
	 * @return
	 */
	public String getNs_city() {
		return ns_city;
	}

	/**
	 * Get Entity NameSpace
	 * 
	 * @return
	 */
	public String getNs_entity() {
		return ns_entity;
	}

	/**
	 * Get Location NameSpace
	 * 
	 * @return
	 */
	public String getNs_location() {
		return ns_location;
	}

	public String getNamespace() {
		return namespace;
	}

}



