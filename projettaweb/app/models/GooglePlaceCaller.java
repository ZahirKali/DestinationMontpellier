package models;

import com.google.gson.GsonBuilder;

public class GooglePlaceCaller {

	int radius = 5000;
	String serverurl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?sensor=false&radius=5000&location=";
	String key = "&key=AIzaSyD9_YnuHc2OkfKWfdVKMiMT2eDKqYdaNRQ";

	String searchurl = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=";
	
	
	String nextpage ="https://maps.googleapis.com/maps/api/place/nearbysearch/json?sensor=false&pagetoken=";

	public GooglePlaceCaller() {
	}

	public GooglePlaceCaller(int radius) {
		this.radius = radius;
	}

	/**
	 * Get Location from city name
	 * 
	 * @param villename
	 * @return
	 */
	private String locationFromVilleName(String villename) {
		String uri = searchurl + villename;

		String result = ApiCaller.cUrl(ApiCaller.getUrlFromString(uri));

		SearchResult r = new GsonBuilder().create().fromJson(result,
				SearchResult.class);

		if (r.getResults().size() != 1) {
			return null;
		} else {
			Geometry g = r.getResults().get(0).getGeometry();
			String ret = g.getLocation().getLat().toString() + ","
					+ g.getLocation().getLng().toString();
			return ret;
		}

	}

	/**
	 * Get Places of given ville name
	 * 
	 * @param villename
	 * @return
	 */
	public Ville villeEntitiesFromWeb(String villename) {
		String loc = locationFromVilleName(villename);
		if (loc != null) {
			String uri = serverurl + loc + key;

			String result = ApiCaller.cUrl(ApiCaller.getUrlFromString(uri));
			Ville ret = new GsonBuilder().create()
					.fromJson(result, Ville.class);

			
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
}
