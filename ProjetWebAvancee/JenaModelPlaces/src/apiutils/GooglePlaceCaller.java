package apiutils;

import googleplaces.Geometry;
import googleplaces.SearchResult;
import googleplaces.Ville;
import com.google.gson.GsonBuilder;

public class GooglePlaceCaller {

	int radius =5000;
	String serverurl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?sensor=false&radius=5000&location=";
	String key = "&key=AIzaSyD9_YnuHc2OkfKWfdVKMiMT2eDKqYdaNRQ";
	String type = "";
	
	String searchurl = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=";
	String nextpage ="https://maps.googleapis.com/maps/api/place/nearbysearch/json?sensor=false&pagetoken=";

	public GooglePlaceCaller() {}

	public GooglePlaceCaller(int radius) {this.radius = radius;	}
	
	private String locationFromVilleName(String villename) {
		String uri = searchurl + villename;
		
		//result contiens le contenu JSON
		String result = ApiCaller.cUrl(ApiCaller.getUrlFromString(uri));
		
		SearchResult r = new GsonBuilder().create().fromJson(result, SearchResult.class);
		
		
		if (r.getResults().size() != 1) {
			return null;
		} else {
			Geometry g = r.getResults().get(0).getGeometry();
			String ret = g.getLocation().getLat().toString() + ","
					+ g.getLocation().getLng().toString();
			return ret;
		}
	}
	/****************************************************************************************************
	 * RECHERCHE DE TOUTES LES ENTITEES 
	 ****************************************************************************************************/
	public Ville villeEntitiesFromWeb(String villename) {
		String loc = locationFromVilleName(villename);
		
		if (loc != null) {
			String uri = serverurl + loc +type+ key;
			String result = ApiCaller.cUrl(ApiCaller.getUrlFromString(uri));
			Ville ret = new GsonBuilder().create().fromJson(result, Ville.class);
			
			
			while (ret.getNext_page_token()!=null){
				uri = nextpage + ret.getNext_page_token() +key;
				result = ApiCaller.cUrl(ApiCaller.getUrlFromString(uri));
				ret.Append(new GsonBuilder().create().fromJson(result, Ville.class));
			}
			
			return ret;

		} else {
			try {
				throw new Exception("Many or Not exist city");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	/****************************************************************************************************
	 * RECHERCHE DES AEROPORTS
	 ****************************************************************************************************/
	public Ville villeAirportsFromWeb(String villename) {
		type = "&types=airport";
		return villeEntitiesFromWeb(villename);
	}
	/****************************************************************************************************
	 * RECHERCHE DES FOODs
	 ****************************************************************************************************/
	public Ville villeFoodsFromWeb(String villename) {
		type = "&types=food";
		return villeEntitiesFromWeb(villename);
	}
	/****************************************************************************************************
	 * RECHERCHE DES MUSEUM
	 ****************************************************************************************************/
	public Ville villeMuseumsFromWeb(String villename) {
		type = "&types=museum";
		return villeEntitiesFromWeb(villename);
	}
	
	/****************************************************************************************************
	 * RECHERCHE DES LODGING
	 ****************************************************************************************************/
	public Ville villeLodgingsFromWeb(String villename) {
		type = "&types=lodging";
		return villeEntitiesFromWeb(villename);
	}
}
