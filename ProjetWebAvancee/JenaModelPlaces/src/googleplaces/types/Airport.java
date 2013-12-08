package googleplaces.types;

import googleplaces.Entity;

import java.util.ArrayList;
import java.util.List;
public class Airport extends Entity{
	public Airport(){
		this.types = new ArrayList<>();
		types.add("airport");
	}
}
