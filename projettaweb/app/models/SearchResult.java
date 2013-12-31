package models;

import java.util.List;

public class SearchResult {
	
	List<LocationResult> results; 
	
	public class LocationResult{
		Geometry geometry;
		public Geometry getGeometry() {
			return geometry;
		}
	}
	
	
	public List<LocationResult> getResults() {
		return results;
	}
}
