package googleplaces.types;

import googleplaces.Entity;

import java.util.ArrayList;
public class Airport extends Entity{
	
	public Airport(){
		this.types = new ArrayList<>();
		types.add("airport");
	}
}
