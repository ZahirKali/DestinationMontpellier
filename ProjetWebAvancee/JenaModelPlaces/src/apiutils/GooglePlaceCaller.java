package apiutils;

import googleplaces.CityInfo;
import googleplaces.CityInfo.component;
import googleplaces.Geometry;
import googleplaces.SearchResult;
import googleplaces.City;
import googleplaces.SearchResult.LocationResult;

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
	
	private String locationFromcityName(String cityName) {
		String uri = searchurl + cityName;
		
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
	 * RETOURNER LES DETAILS D'UNE VILLE
	 ****************************************************************************************************/
	private CityInfo getCityInfo(String cityName){
		String uri = searchurl + cityName;
		String result = ApiCaller.cUrl(ApiCaller.getUrlFromString(uri));
		
		CityInfo c = new GsonBuilder().create().fromJson(result, CityInfo.class);
		return c;
	}
	
	/****************************************************************************************************
	 * RECHERCHE DE TOUTES LES ENTITEES 
	 ****************************************************************************************************/
	public City villeEntitiesFromWeb(String cityName){
		String loc = locationFromcityName(cityName);
		
		if (loc != null) {
			String uri = serverurl + loc +type+ key;
			String result = ApiCaller.cUrl(ApiCaller.getUrlFromString(uri));
			City ret = new GsonBuilder().create().fromJson(result, City.class);
			ret.setDetails(getCityInfo(cityName)); //les infos de la ville
			
			
			while (ret.getNext_page_token()!=null){
				uri = nextpage + ret.getNext_page_token() +key;
				result = ApiCaller.cUrl(ApiCaller.getUrlFromString(uri));
				ret.Append(new GsonBuilder().create().fromJson(result, City.class));
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
	public City villeAirportsFromWeb(String cityName) {
		type = "&types=airport";
		return villeEntitiesFromWeb(cityName);
	}
	/****************************************************************************************************
	 * RECHERCHE DES FOODs
	 ****************************************************************************************************/
	public City villeFoodsFromWeb(String cityName) {
		type = "&types=food";
		return villeEntitiesFromWeb(cityName);
	}
	/****************************************************************************************************
	 * RECHERCHE DES MUSEUM
	 ****************************************************************************************************/
	public City villeMuseumsFromWeb(String cityName) {
		type = "&types=museum";
		return villeEntitiesFromWeb(cityName);
	}
	
	/****************************************************************************************************
	 * RECHERCHE DES LODGING
	 ****************************************************************************************************/
	public City villeLodgingsFromWeb(String cityName) {
		type = "&types=lodging";
		return villeEntitiesFromWeb(cityName);
	}
}
