
package googleplaces;


public class Viewport{
   	private Location northeast;
   	private Location southwest;

 	public Location getNortheast(){
		return this.northeast;
	}
	public void setNortheast(Location northeast){
		this.northeast = northeast;
	}
 	public Location getSouthwest(){
		return this.southwest;
	}
	public void setSouthwest(Location southwest){
		this.southwest = southwest;
	}
}
